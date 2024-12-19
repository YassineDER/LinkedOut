package org.ichat.backend.config.security;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.config.requests.JwtAuthenticationFilter;
import org.ichat.backend.model.util.auth.RoleType;
import org.ichat.backend.services.account.IJwtService;
import org.ichat.backend.services.account.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class that configures the security of the application.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Enables Spring Security beans
@EnableMethodSecurity // Enables all methods to have security annotations like @PreAuthorize
public class SecurityConfiguration {
    private final IJwtService jwtService;
    private final IUserService userService;
    @Qualifier("customAuthenticationEntryPoint")
    private final AuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, userService, entryPoint);

        http.csrf(AbstractHttpConfigurer::disable) // CSRF should be disabled when using JWT
                .anonymous(AbstractHttpConfigurer::disable) // Disable default anonymous filter
                .cors(Customizer.withDefaults()) // Enabled by default, customized in the addCorsMappings method bellow
                .formLogin(AbstractHttpConfigurer::disable) // Disable default form login, we use JWT
                .logout(AbstractHttpConfigurer::disable) // Disable default logout, we use JWT and session is stateless
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Custom JWT filter defined above
                .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint)) // Custom entry point for formating error messages
                .authorizeHttpRequests(authorize -> authorize // Authorize requests based on roles
                        .requestMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
                        .requestMatchers("/api/jobseeker/**").hasAuthority(RoleType.JOBSEEKER.name())
                        .requestMatchers("/api/company/**").hasAuthority(RoleType.COMPANY.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Customizes the web security configuration to ignore requests to the auth endpoint.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Allow all requests to the root and auth endpoint
        return (web) -> web.ignoring()
                .requestMatchers("/api/auth/**");
    }

}

