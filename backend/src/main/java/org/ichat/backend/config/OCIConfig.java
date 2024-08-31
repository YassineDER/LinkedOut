package org.ichat.backend.config;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimpleAuthenticationDetailsProvider;
import com.oracle.bmc.auth.StringPrivateKeySupplier;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class OCIConfig {
    private static final String user = System.getenv("OCI_USER");
    private static final String fingerprint = System.getenv("OCI_FINGERPRINT");
    private static final String tenancy = System.getenv("OCI_TENANCY");
    private static final String key = System.getenv("OCI_KEY_B64");

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
