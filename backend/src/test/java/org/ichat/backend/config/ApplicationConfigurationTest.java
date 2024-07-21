package org.ichat.backend.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationConfigurationTest {
    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager manager;

    ApplicationConfiguration configuration;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        configuration = new ApplicationConfiguration(userDetailsService);
    }

    @Test
    public void authenticationProviderTest() {
        AuthenticationProvider provider = configuration.authenticationProvider();
        assertThat(provider).isNotNull();
    }

    @Test
    void authenticationManagerBeanTest() throws Exception {
        // Given
        AuthenticationConfiguration authenticationConfiguration = mock(AuthenticationConfiguration.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(manager);
        // When
        AuthenticationManager authmanager = configuration.authenticationManager(authenticationConfiguration);
        // Then
        assertThat(authmanager).isNotNull();
    }

    @Test
    public void passwordEncoderTest() {
        // When
        PasswordEncoder encoder = configuration.passwordEncoder();
        // Then
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
        assertThat(encoder).isNotNull();
    }
}