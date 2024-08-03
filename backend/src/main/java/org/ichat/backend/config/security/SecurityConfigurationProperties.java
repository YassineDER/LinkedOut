package org.ichat.backend.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@AllArgsConstructor
@ConfigurationProperties("security")
public class SecurityConfigurationProperties {
    private final List<String> allowedOrigins;
}
