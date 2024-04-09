package org.ichat.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.json.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private static final String[] INPI_ACCOUNT = System.getenv("INPI_ACCOUNT").split(";");
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

    //every 50 minutes
    @Scheduled(fixedRate = 3000000)
    public void setNewInpiToken() {
        RestClient client = RestClient.create();
        String response = client.post()
                .uri("https://registre-national-entreprises.inpi.fr/api/sso/login")
                .header("Content-Type", "application/json")
                .body(Map.of("username", INPI_ACCOUNT[0], "password", INPI_ACCOUNT[1]))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);

        JSONObject json = new JSONObject(response);
        String token = json.getString("token");
        System.setProperty("INPI_TOKEN", token);
        client.delete();
    }

}
