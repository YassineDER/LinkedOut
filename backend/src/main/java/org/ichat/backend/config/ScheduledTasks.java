package org.ichat.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.services.account.IUserService;
import org.ichat.backend.services.shared.IStorageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

/**
 * Scheduled tasks for some cleanup operations.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountResetRepository accountResetRepository;
    private final IStorageService storageService;
    private final IUserService userService;

    // Delete expired account verifications tokens every hour
    @Scheduled(cron = "0 0 */1 * * ?")
    public void deleteExpiredAccountVerification() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountVerificationRepository.deleteByExpiresAtBefore(threshold);
        log.info("Deleted expired account verification tokens");
    }

    // Delete expired account password reset every 12 hours
    @Scheduled(cron = "0 0 */12 * * ?")
    public void deleteExpiredAccountReset() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        accountResetRepository.deleteByExpiresAtBefore(threshold);
        log.info("Deleted expired account reset tokens");
    }

    // every 3 hours
    @Scheduled(cron = "0 0 */3 * * ?")
    public void deleteExpiredPAR() {
        OffsetDateTime threshold = OffsetDateTime.now();
        storageService.deleteExpiredPARs(threshold);
        log.info("Deleted expired OCI preauthenticated requests");
    }

    // every 24 hours
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredImages() {
        storageService.deleteUnusedImages(userService);
        log.info("Deleted expired images");
    }

    // Clear image cache every 3 hours (default, defined in application.yml)
    @Scheduled(fixedRateString = "${spring.caching.user.images}")
    @CacheEvict(value = {"images"}, allEntries = true)
    public void clearImageCache() {
        log.info("Cleared cache.");
    }

}
