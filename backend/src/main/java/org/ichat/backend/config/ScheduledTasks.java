package org.ichat.backend.config;

import lombok.AllArgsConstructor;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.repository.TwoFactorAuthenticationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class ScheduledTasks {
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountResetRepository accountResetRepository;
    private final TwoFactorAuthenticationRepository twoFactorAuthenticationRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountVerification() {
        LocalDateTime dayAgo = LocalDateTime.now().minusHours(24);
        Date threshold = java.sql.Timestamp.valueOf(dayAgo);
        accountVerificationRepository.deleteAllExpiredSince(threshold);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountReset() {
        LocalDateTime dayAgo = LocalDateTime.now().minusHours(24);
        Date threshold = java.sql.Timestamp.valueOf(dayAgo);
        accountResetRepository.deleteAllExpiredSince(threshold);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredTwoFactorAuthentication() {
        LocalDateTime halfDayAgo = LocalDateTime.now().minusHours(12);
        Date threshold = java.sql.Timestamp.valueOf(halfDayAgo);
        twoFactorAuthenticationRepository.deleteAllExpiredSince(threshold);
    }

}
