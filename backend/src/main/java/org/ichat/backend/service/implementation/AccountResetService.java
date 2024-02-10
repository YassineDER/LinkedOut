package org.ichat.backend.service.implementation;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.AccountReset;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.service.IAccountResetService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountResetService implements IAccountResetService {
    private final AccountResetRepository accountResetRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public String sendResetEmail(String email) {
        String uuid_part = StringUtils.split(UUID.randomUUID().toString(), "-")[0];
        String unique = email + ":" + uuid_part;
        String token = UUID.nameUUIDFromBytes(unique.getBytes()).toString();

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/auth/verify/password/" + token)
                .toUriString();

        String header = "Reset your password - Securecapita";
        Context context = new Context();
        context.setVariables(Map.of(
                "header", header,
                "title", "Reset your password",
                "link", url));

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(header);
            helper.setFrom("no-reply@securecapita.com");

            String mailContent = templateEngine.process("reset", context);
            helper.setText(mailContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new AccountException("Failed to send reset email. " + e.getMessage());
        }

        return token;
    }

    @Override
    public String resetPassword(String token, String encodedPassword) {
        var accountReset = accountResetRepository.findByToken(token)
                .orElseThrow(() -> new AccountException("No password reset request found with the provided token"));
        if (accountReset.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has expired, please retry to send another request.");

        User user = accountReset.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. This is an unexpected behaviour.");

        accountReset.resetUserPassword(encodedPassword);
        accountResetRepository.save(accountReset);
        return "Password has been reset successfully, you can login with your new password";
    }

    @Override
    public void saveReset(User user, String resetToken) {
        accountResetRepository.deleteByUser(user);

        AccountReset accountReset = new AccountReset();
        accountReset.setUser(user);
        accountReset.setToken(resetToken);
        var expiresAt = java.time.OffsetDateTime.now().plusMinutes(10);
        accountReset.setExpiresAt(expiresAt);

        accountResetRepository.save(accountReset);
    }
}
