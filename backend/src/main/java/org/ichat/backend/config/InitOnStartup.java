package org.ichat.backend.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.repository.AdminRepository;
import org.ichat.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitOnStartup {
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepo;
    private final PasswordEncoder encoder;

    @Bean
    @PostConstruct
    public void createRoles() {
        List<Roles> roles = roleRepository.findAll();
        if (roles.isEmpty())
            roleRepository.saveAll(List.of(new Roles(1, "JOBSEEKER"),
                    new Roles(2, "ADMIN"), new Roles(3, "COMPANY")));
    }

    @Bean
    public CommandLineRunner createAdminIfNotExists() {
        return args -> {
            List<Admin> admins = adminRepo.findAll();
            Roles adminRole = roleRepository.findByName("ADMIN").get();
            Admin admin = new Admin("Yassine", "Dergaoui", "0605897043", "Owner");
            admin.setUser_id(1L);
            admin.setRole(adminRole);
            admin.setEnabled(true);
            admin.setImage_url("https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/images/o/default_profile.jpg");

            if (admins.isEmpty()) {
                admin.setEmail("admin@example.com");
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("12345678"));
                adminRepo.save(admin);
                System.out.println("Admin not found, created a new one with default credentials");
            }
        };
    }

}
