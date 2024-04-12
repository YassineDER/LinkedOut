package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.AccountReset;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.service.IAccountResetService;
import org.ichat.backend.service.IMailService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountResetService implements IAccountResetService {
    private final AccountResetRepository accountResetRepository;
    private final IMailService mailService;

    @Override
    public String sendResetEmail(String email) {
        int number = new Random().nextInt(999999);
        String code = String.format("%06d", number);

        mailService.sendMail(email, "Password Reset Request", code, MailType.RESET_PASSWORD);
        return code;
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
