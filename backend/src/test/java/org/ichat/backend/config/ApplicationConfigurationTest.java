package org.ichat.backend.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ApplicationConfigurationTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private AuthenticationManager manager;

    @Test
    public void setup() {
        assertThat(userDetailsService).isNotNull();
        assertThat(provider).isNotNull();
        assertThat(manager).isNotNull();
    }

    @Test
    public void authenticationProviderReturnsDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        assertThat(provider).isNotNull();
    }

    @Test
    public void passwordEncoderReturnsBCryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertThat(passwordEncoder).isNotNull();
    }
}