package org.ichat.backend.config.security;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.core.UserArgumentResolver;
import org.ichat.backend.services.account.IUserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * This class is responsible for custom-configuring the MVC settings of the application.
 * It enables CORS, and adds the UserArgumentResolver to the list of argument resolvers.
 */
@Configuration
@EnableConfigurationProperties(SecurityConfigurationProperties.class)
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfiguration implements WebMvcConfigurer {
    private final SecurityConfigurationProperties properties;
    private final IUserService userService;

    // Allowed origins are defined in the application.yml file (based on the profile env)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedOrigins(properties.getAllowedOrigins().toArray(new String[0]))
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // Supply the user object to the controller methods
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver(userService.getUserRepo()));
    }


}