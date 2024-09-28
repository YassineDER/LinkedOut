package org.ichat.backend.core;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.util.auth.RoleType;
import org.ichat.backend.repository.account.AdminRepository;
import org.ichat.backend.repository.account.JobseekerRepository;
import org.ichat.backend.repository.account.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsersLoader {
    private final JobseekerRepository jobseekerRepo;
    private final RoleRepository roleRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder encoder;

    @Bean
    @PostConstruct
    @Order(1)
    public void createRoles() {
        List<Roles> roles = roleRepo.findAll();
        if (roles.isEmpty()) {
            roleRepo.saveAll(List.of(new Roles(1, RoleType.JOBSEEKER),
                    new Roles(2, RoleType.ADMIN), new Roles(3, RoleType.COMPANY)));
            adminRepo.flush(); // createAdminIfNotExists depends on the roles being saved, so the transaction must be committed
            log.info("Roles created successfully");
        }
    }

    @Bean
    @Order(2)
    public void createAdminIfNotExists() {
        List<Admin> admins = adminRepo.findAll();
        if (admins.isEmpty()) {
            Roles adminRole = roleRepo.findByName(RoleType.ADMIN).orElseThrow();
            Admin admin = new Admin("Yassine", "Dergaoui", "0605897043", "Owner");
            admin.setUser_id(1L);
            admin.setRole(adminRole);
            admin.setEnabled(true);
            CompanyStaffProfile profile = new CompanyStaffProfile();
            profile.setUser(admin);
            admin.setProfile(profile);
            admin.setEmail("admin@example.com");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("12345678"));
            adminRepo.save(admin);
            adminRepo.flush(); // createTemplatePosts depends on the admin being saved, so the transaction must be committed
            log.info("Admin not found, created a new one with default credentials");
        }
    }

    @Bean
    @Order(3)
    public void createJobseekers() {
        boolean haveNoJobseekers = jobseekerRepo.findAll().isEmpty();
        if (haveNoJobseekers) {
            Roles jobseekerRole = roleRepo.findByName(RoleType.JOBSEEKER).orElseThrow();

            // First Jobseeker
            Jobseeker jobseeker1 = new Jobseeker();
            jobseeker1.setUser_id(152L);
            jobseeker1.setRole(jobseekerRole);
            jobseeker1.setEnabled(true);
            JobseekerProfile profile1 = new JobseekerProfile();
            profile1.setUser(jobseeker1);
            jobseeker1.setProfile(profile1);
            jobseeker1.setFirst_name("John");
            jobseeker1.setLast_name("Doe");
            jobseeker1.setEmail("sender@example.com");
            jobseeker1.setUsername("jobseeker1");
            jobseeker1.setPassword(encoder.encode("12345678"));

            // Second Jobseeker
            Jobseeker jobseeker2 = new Jobseeker();
            jobseeker2.setUser_id(153L);
            jobseeker2.setRole(jobseekerRole);
            jobseeker2.setEnabled(true);
            JobseekerProfile profile2 = new JobseekerProfile();
            jobseeker2.setProfile(profile2);
            profile2.setUser(jobseeker2);
            jobseeker2.setFirst_name("Jane");
            jobseeker2.setLast_name("Doe");
            jobseeker2.setEmail("receiver@example.com");
            jobseeker2.setUsername("jobseeker2");
            jobseeker2.setPassword(encoder.encode("12345678"));

            jobseekerRepo.saveAll(List.of(jobseeker1, jobseeker2));
            log.info("No jobseekers found, created some.");
        }
    }
}
