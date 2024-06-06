package org.ichat.backend.config;

import org.ichat.backend.model.tables.indentity.AccountReset;
import org.ichat.backend.model.tables.indentity.AccountVerification;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduledTasksTest {
    @Autowired
    private AccountVerificationRepository accountVerificationRepository;
    @Autowired
    private AccountResetRepository accountResetRepository;
    @Autowired
    private ScheduledTasks scheduledTasks;
    @Autowired
    private CacheConfig cacheConfig;

    @BeforeEach
    void setUp() {
        accountVerificationRepository.deleteAll();
        accountResetRepository.deleteAll();
    }

    @Test
    void deleteExpiredAccountVerification() {
        OffsetDateTime oldDate = OffsetDateTime.now().minusHours(24);
        AccountVerification oldToken = new AccountVerification();
        oldToken.setToken("oldToken");
        oldToken.setExpiresAt(oldDate);

        accountVerificationRepository.save(oldToken);
        assertTrue(accountVerificationRepository.findByToken("oldToken").isPresent());
        scheduledTasks.deleteExpiredAccountVerification();
        assertFalse(accountVerificationRepository.findByToken("oldToken").isPresent());
    }

    @Test
    void deleteExpiredAccountReset() {
        OffsetDateTime oldDate = OffsetDateTime.now().minusHours(24);
        AccountReset oldToken = new AccountReset();
        oldToken.setToken("oldToken");
        oldToken.setExpiresAt(oldDate);

        accountResetRepository.save(oldToken);
        assertTrue(accountResetRepository.findByToken("oldToken").isPresent());
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

}