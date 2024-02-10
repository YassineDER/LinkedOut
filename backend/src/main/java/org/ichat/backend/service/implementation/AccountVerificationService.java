package org.ichat.backend.service.implementation;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.jwt.IJwtService;
import org.ichat.backend.model.AccountVerification;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.service.IAccountVerificationService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountVerificationService implements IAccountVerificationService {
    private final AccountVerificationRepository accountVerificationRepository;
    private final JavaMailSender mailSender;
    private final IJwtService jwtServiceV2;
    private final TemplateEngine templateEngine;

    @Override
    public String verifyToken(String token) {
        var accountVerification = accountVerificationRepository.findByToken(token)
                .orElseThrow(() -> new AccountException("No account verification found with the provided token"));
        if (accountVerification.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has expired, please try to login to send a new one");

        User user = accountVerification.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. Please register again.");
        if (user.getEnabled())
            throw new AccountException("User account is already verified and enabled");

        accountVerification.verifyUser();
        accountVerificationRepository.save(accountVerification);
        return jwtServiceV2.generateToken(user);
    }

    @Override
    public String sendVerificationEmail(String email) {
        String token = UUID.randomUUID().toString();
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/auth/verify/" + token)
                .toUriString();

        String header = "Verify your email address - Securecapita";
        Context context = new Context();
        context.setVariables(Map.of(
                "header", header,
                "title", "Verify your email address",
                "link", url));

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(header);
            helper.setFrom("no-reply@securecapita.com");
            
            String mailContent = templateEngine.process("email", context);
            helper.setText(mailContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new AccountException("Failed to send verification email: " + e.getMessage());
        }

        return token;
    }

    @Override
    public void saveVerification(User user, String token) {
        accountVerificationRepository.deleteByUser(user);

        AccountVerification accountVerification = new AccountVerification();
        accountVerification.setUser(user);
        accountVerification.setToken(token);
        accountVerification.setExpiresAt(OffsetDateTime.now().plusMinutes(35));

        accountVerificationRepository.save(accountVerification);
    }
}
