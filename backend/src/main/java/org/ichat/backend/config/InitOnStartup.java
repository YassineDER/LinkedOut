package org.ichat.backend.config;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Roles;
import org.ichat.backend.repository.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitOnStartup {
    private final RoleRepository roleRepository;

    @Bean
    public void initDatabase() {

        List<Roles> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            roleRepository.saveAll(List.of(new Roles(1, "JOBSEEKER"),
                    new Roles(2, "ADMIN"), new Roles(3, "COMPANY")));
        }
    }

}
