package org.ichat.backend.config;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationConfigurationTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @InjectMocks
    private ApplicationConfiguration applicationConfiguration;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticationProviderReturnsDaoAuthenticationProvider() {
        AuthenticationProvider provider = applicationConfiguration.authenticationProvider();
        assertNotNull(provider);
        assert (provider instanceof DaoAuthenticationProvider);
    }

    @Test
    public void authenticationManagerReturnsAuthenticationManager() throws Exception {
        AuthenticationManager mockManager = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager manager = applicationConfiguration.authenticationManager(authenticationConfiguration);
        assertNotNull(manager);
    }

    @Test
    public void passwordEncoderReturnsBCryptPasswordEncoder() {
        PasswordEncoder encoder = applicationConfiguration.passwordEncoder();
        assertNotNull(encoder);
    }
}