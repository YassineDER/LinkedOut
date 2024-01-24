package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.AccountVerification;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.service.IAccountVerificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountVerificationService implements IAccountVerificationService {
    private final AccountVerificationRepository accountVerificationRepository;
    private final JavaMailSender mailSender;

    @Override
    public User verifyToken(String token) {
        AccountVerification accountVerification = accountVerificationRepository.findByToken(token);
        if (accountVerification == null)
            throw new AccountException("No account verification found with the provided token");
        if (accountVerification.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has expired, please try to login to send a new one");

        User user = accountVerification.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. Please register again.");
        if (user.getEnabled())
            throw new AccountException("User account is already verified and enabled");

        accountVerification.verifyUser();
        accountVerificationRepository.save(accountVerification);
        return user;
    }

    @Override
    public void sendVerificationEmail(User user) {
        UUID token = UUID.randomUUID();
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/verify/" + token)
                .toUriString();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Verify your email address - Securecapita");
        mailMessage.setText("Please click the link below to verify your email address: \n" + url);
        mailSender.send(mailMessage);

        AccountVerification accountVerification = new AccountVerification();
        accountVerification.setUser(user);
        accountVerification.setToken(token.toString());
        accountVerification.setExpiresAt(OffsetDateTime.now().plusMinutes(30));

        accountVerificationRepository.save(accountVerification);
    }
}
