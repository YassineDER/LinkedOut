package org.ichat.backend.config;

import org.aspectj.lang.annotation.Before;
import org.ichat.backend.model.AccountReset;
import org.ichat.backend.model.AccountVerification;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ScheduledTasksTest {
    @Autowired
    private AccountVerificationRepository accountVerificationRepository;
    @Autowired
    private AccountResetRepository accountResetRepository;
    @Autowired
    private ScheduledTasks scheduledTasks;
    @Autowired
    private CacheConfig cacheConfig;


    @Test
    void deleteExpiredAccountVerification() {
        OffsetDateTime oldDate = OffsetDateTime.now().minusDays(2);
        AccountVerification oldToken = new AccountVerification();
        oldToken.setToken("oldToken");
        oldToken.setExpiresAt(oldDate);
        if (accountVerificationRepository.findByToken("oldToken").isPresent())
            accountVerificationRepository.deleteByToken("oldToken");
        accountVerificationRepository.save(oldToken);
        scheduledTasks.deleteExpiredAccountVerification();
        assertFalse(accountVerificationRepository.findByToken("oldToken").isPresent());
    }

    @Test
    void deleteExpiredAccountReset() {
        OffsetDateTime oldDate = OffsetDateTime.now().minusDays(2);
        AccountReset oldToken = new AccountReset();
        oldToken.setToken("oldToken");
        oldToken.setExpiresAt(oldDate);
        if (accountResetRepository.findByToken("oldToken").isPresent())
            accountResetRepository.deleteByToken("oldToken");
        accountResetRepository.save(oldToken);
        scheduledTasks.deleteExpiredAccountReset();
        assertFalse(accountResetRepository.findByToken("oldToken").isPresent());
    }

    @Test
    void clearImageCache() {
        CacheManager cacheManager = cacheConfig.cacheManager();
        Cache imagesCache = cacheManager.getCache("images");
        imagesCache.put("key", "value");

        assertNotNull(imagesCache.get("key"));
        scheduledTasks.clearImageCache();
        assertNull(imagesCache.get("key"));
    }

    @Test
    void setNewInpiToken() {
        scheduledTasks.setNewInpiToken();
        assertFalse(System.getProperty("INPI_TOKEN").isEmpty());
        assertTrue(System.getProperty("INPI_TOKEN").length() > 1);
    }
}