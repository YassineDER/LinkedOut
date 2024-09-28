package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.core.AsyncHelper;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.indentity.AccountVerification;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.repository.account.AccountVerificationRepository;
import org.ichat.backend.services.shared.IMailService;
import org.ichat.backend.services.account.IAccountVerificationService;
import org.ichat.backend.services.account.IJwtService;
import org.springframework.http.HttpStatus;
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

    @Override
    public String verifyEmailCode(String code) {
        var existingVerif = accountVerificationRepository.findByToken(code)
                .orElseThrow(() -> new AccountException("Account verification not found or has expired a long time ago", HttpStatus.NOT_FOUND.value()));

        if (existingVerif.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has just expired, please try to login to send a new one", HttpStatus.GONE.value());

        User user = existingVerif.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. Please register again.", HttpStatus.NOT_FOUND.value());
        if (user.getEnabled())
            throw new AccountException("User of this account verification is already verified", HttpStatus.CONFLICT.value());

        user.setEnabled(true);
        accountVerificationRepository.deleteByUser(user);
        return jwtService.generateToken(user);
    }

    @Override
    public void verifyMfaRequest(User user, String code) {
        var existingVerif = accountVerificationRepository.findByToken(code)
                .orElseThrow(() -> new AccountException("Account verification not found or has expired a long time ago", HttpStatus.NOT_FOUND.value()));
        if (existingVerif.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token is invalid or has just expired, please retry to send a new one", HttpStatus.GONE.value());

        accountVerificationRepository.deleteByUser(user);
    }

    @Override
    public String sendVerificationEmail(String email) {
        int number = new Random().nextInt(999999);
        String code = String.format("%06d", number);
        Runnable operation = () -> mailService.sendMail(email, "Verifiez votre adresse mail - LinkedOut", code, MailType.VERIFY_ACCOUNT);
        AsyncHelper.performEmailRateLimit(operation, email);
        return code;
    }

    @Override
    public void saveVerificationRequest(User user, String code) {
        accountVerificationRepository.deleteByUser(user);

        AccountVerification accountVerification = new AccountVerification();
        accountVerification.setUser(user);
        accountVerification.setToken(code);
        accountVerification.setExpiresAt(OffsetDateTime.now().plusMinutes(35));

        accountVerificationRepository.save(accountVerification);
    }
}
