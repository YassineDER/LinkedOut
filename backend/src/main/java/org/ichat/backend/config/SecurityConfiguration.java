package org.ichat.backend.config;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.config.requests.JwtAuthenticationFilter;
import org.ichat.backend.service.account.IJwtService;
import org.ichat.backend.service.account.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
public class SecurityConfiguration implements WebMvcConfigurer {
    private final AuthenticationProvider authProvider;
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
                .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint)) // Custom entry point for formating error messages
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/jobseeker/**").hasAuthority("JOBSEEKER")
                        .requestMatchers("/api/company/**").hasAuthority("COMPANY")
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider); // Custom authentication provider

        return http.build();
    }

    // Supply the user object to the controller methods
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver(userService.getUserRepository()));
    }
}

