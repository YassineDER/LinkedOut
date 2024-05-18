package org.ichat.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import static org.junit.jupiter.api.Assertions.*;

class CacheConfigTest {

    @Test
    void cacheManagerTest() {
        CacheManager cacheManager = new CacheConfig().cacheManager();
        assertNotNull(cacheManager);
        assertInstanceOf(ConcurrentMapCacheManager.class, cacheManager);
        assertTrue(cacheManager.getCacheNames().contains("images"));
        assertNull(cacheManager.getCache("non-existing"));
    }
}