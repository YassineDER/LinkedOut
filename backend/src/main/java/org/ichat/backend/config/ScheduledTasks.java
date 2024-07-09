package org.ichat.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountResetRepository accountResetRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountVerification() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountVerificationRepository.deleteByExpiresAtBefore(threshold);
        log.info("Deleted expired account verification tokens");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredAccountReset() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountResetRepository.deleteByExpiresAtBefore(threshold);
        log.info("Deleted expired account reset tokens");
    }

    @Scheduled(fixedRateString = "${spring.caching.user.images}")
    @CacheEvict(value = {"images"}, allEntries = true)
    public void clearImageCache() {
        log.info("Cleared cache.");
        
    }

}
