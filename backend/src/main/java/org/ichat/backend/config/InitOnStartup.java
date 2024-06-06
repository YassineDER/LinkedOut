package org.ichat.backend.config;

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
            Admin admin = new Admin("Yassine", "Dergaoui", "0605897043", "Owner");
            admin.setUser_id(1L);
            admin.setUser_roles(Set.of(roleRepository.findByName("ADMIN").get()));
            admin.setEnabled(true);

            if (admins.isEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("No admin found, please create one: ");
                System.out.println("Enter the admin email: ");
                admin.setEmail(scanner.nextLine());
                System.out.println("Enter the admin username: ");
                admin.setUsername(scanner.nextLine());
                System.out.println("Enter the admin password: ");
                String password = scanner.nextLine();
                System.out.println("Enter the admin password again: ");
                String password2 = scanner.nextLine();
                while (!password.equals(password2)) {
                    System.out.println("Passwords do not match, please try again: ");
                    System.out.println("Enter the admin password: ");
                    password = scanner.nextLine();
                    System.out.println("Enter the admin password again: ");
                    password2 = scanner.nextLine();
                }
                admin.setPassword(encoder.encode(password));
                adminRepo.save(admin);
                
                System.out.println("An admin has been created successfully.");
            }
        };
    }

}
