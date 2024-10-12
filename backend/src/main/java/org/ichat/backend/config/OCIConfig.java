package org.ichat.backend.config;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimpleAuthenticationDetailsProvider;
import com.oracle.bmc.auth.StringPrivateKeySupplier;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class OCIConfig {
    @Value("${oci.user}")
    private String user;
    @Value("${oci.fingerprint}")
    private String fingerprint;
    @Value("${oci.tenancy}")
    private String tenancy;
    @Value("${oci.key}")
    private String key;

    private ObjectStorageClient client;

    /**
     * Initialize the storage service from a specific authentication configuration
     */
    @Bean
    public ObjectStorageClient init() {
        String decodedKey = new String(Base64.getDecoder().decode(key), StandardCharsets.UTF_8);
        final AuthenticationDetailsProvider provider =
                SimpleAuthenticationDetailsProvider.builder()
                        .tenantId(tenancy)
                        .userId(user)
                        .fingerprint(fingerprint)
                        .privateKeySupplier(new StringPrivateKeySupplier(decodedKey))
                        .passPhrase(null)
                        .region(Region.EU_PARIS_1)
                        .build();
        System.setProperty("oci.javasdk.extra.stream.logs.enabled", "false");

        client = ObjectStorageClient.builder()
                .build(provider);
        return client;
    }

    @PreDestroy
    public void close() {
        if (client != null)
            client.close();
    }

}
