package org.ichat.backend.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.Roles;
import org.ichat.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@Slf4j
@RequiredArgsConstructor
public class InitDatabase {
    private final RoleRepository roleRepository;

    @Bean
    public void init() {
        List<Roles> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            roleRepository.save(new Roles(1, "USER", "READ"));
            roleRepository.save(new Roles(2, "ADMIN", "READ,WRITE,DELETE"));
            roleRepository.save(new Roles(3, "MANAGER", "READ,WRITE"));
        }
    }

}
