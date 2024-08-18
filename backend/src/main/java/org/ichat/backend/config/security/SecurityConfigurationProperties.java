package org.ichat.backend.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * This class is used to read the properties from the application.yml file.
 */
@Getter
@AllArgsConstructor
@ConfigurationProperties("security") // Defined in application.yml
public class SecurityConfigurationProperties {
    private final List<String> allowedOrigins;
}
