package org.ichat.backend.service.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.indentity.AccountVerification;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.service.shared.IMailService;
import org.ichat.backend.service.account.IAccountVerificationService;
import org.ichat.backend.service.account.IJwtService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountVerificationService implements IAccountVerificationService {
    private final AccountVerificationRepository accountVerificationRepository;
    private final IMailService mailService;
    private final IJwtService jwtService;
    private final Random random = new Random();

    @Override
    public String verifyToken(String token) {
        var accountVerification = accountVerificationRepository.findByToken(token)
                .orElseThrow(() -> new AccountException("No account verification found with the provided token"));
        if (accountVerification.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has expired, please try to login to send a new one");

        User user = accountVerification.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. Please register again.");
        if (Boolean.TRUE.equals(user.getEnabled()))
            throw new AccountException("User account is already verified and enabled");

        accountVerification.verifyUser();
        accountVerificationRepository.save(accountVerification);
        return jwtService.generateToken(user);
    }

    @Override
    public String sendVerificationEmail(String email) {
        int number = random.nextInt(999999);
        String code = String.format("%06d", number);

        mailService.sendMail(email, "Verify your account", code, MailType.VERIFY_ACCOUNT);
        return code;
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
