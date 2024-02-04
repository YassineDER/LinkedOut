package org.ichat.backend.config;

import lombok.AllArgsConstructor;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.repository.TwoFactorAuthenticationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class ScheduledTasks {
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountResetRepository accountResetRepository;
    private final TwoFactorAuthenticationRepository twoFactorAuthenticationRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountVerification() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountVerificationRepository.deleteAllByExpiresAtBefore(threshold);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountReset() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountResetRepository.deleteAllByExpiresAtBefore(threshold);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredTwoFactorAuthentication() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(12);
        twoFactorAuthenticationRepository.deleteAllByExpiresAtBefore(threshold);
    }

}
