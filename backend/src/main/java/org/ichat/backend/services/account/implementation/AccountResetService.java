package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.core.AsyncHelper;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.indentity.AccountReset;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.repository.account.AccountResetRepository;
import org.ichat.backend.services.shared.IMailService;
import org.ichat.backend.services.account.IAccountResetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountResetService implements IAccountResetService {
    private final AccountResetRepository accountResetRepository;
    private final IMailService mailService;
    private final Random random = new Random();

    @Override
    public String sendPasswordResetEmail(String email) {
        int number = random.nextInt(999999);
        String code = String.format("%06d", number);
        Runnable operation = () -> mailService.sendMail(email, "Demande de renitialisation de mot de passe", code, MailType.RESET_PASSWORD);
        AsyncHelper.performEmailRateLimit(operation, email);
        return code;
    }

    @Override
    public String resetPassword(String token, String encodedPassword) {
        var accountReset = accountResetRepository.findByToken(token)
                .orElseThrow(() -> new AccountException("Password reset request not found or has expired a long time ago",
                        HttpStatus.NOT_FOUND.value()));
        if (accountReset.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new AccountException("The provided token has just expired, please retry to send another request",
                    HttpStatus.GONE.value());

        User user = accountReset.getUser();
        if (user == null)
            throw new AccountException("No user found with the provided token. This is an unexpected behaviour", HttpStatus.NOT_FOUND.value());

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
