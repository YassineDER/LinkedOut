package org.ichat.backend.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.Profile;
import org.ichat.backend.model.util.RoleType;
import org.ichat.backend.repository.AdminRepository;
import org.ichat.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is used to execute some operations on application startup.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InitOnStartup {
    private final RoleRepository roleRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder encoder;

    /**
     * This method is used to create roles if they don't exist in the database.
     */
    @Bean
    @PostConstruct
    public void createRoles() {
        List<Roles> roles = roleRepo.findAll();
        if (roles.isEmpty()) {
            roleRepo.saveAll(List.of(new Roles(1, RoleType.JOBSEEKER),
                    new Roles(2, RoleType.ADMIN), new Roles(3, RoleType.COMPANY)));
            log.info("Roles created successfully");
        }
    }

    /**
     * This method is used to create an admin if it doesn't exist in the database.
     * The credentials are the default ones, they must be defined in application.yml
     */
    @Bean
    public CommandLineRunner createAdminIfNotExists() {
        return args -> {
            List<Admin> admins = adminRepo.findAll();
            Roles adminRole = roleRepo.findByName(RoleType.ADMIN).orElseThrow();
            Admin admin = new Admin("Yassine", "Dergaoui", "0605897043", "Owner");
            admin.setUser_id(1L);
            admin.setRole(adminRole);
            admin.setEnabled(true);

            Profile profile = new CompanyStaffProfile();
            profile.setUser(admin);
            admin.setProfile(profile);

            if (admins.isEmpty()) {
                admin.setEmail("admin@example.com");
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("12345678"));
                adminRepo.save(admin);
                log.info("Admin not found, created a new one with default credentials");
            }
        };
    }

}
