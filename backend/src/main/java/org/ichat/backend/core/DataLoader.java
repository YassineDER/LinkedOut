package org.ichat.backend.core;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.util.auth.RoleType;
import org.ichat.backend.repository.account.AdminRepository;
import org.ichat.backend.repository.account.JobseekerRepository;
import org.ichat.backend.repository.account.RoleRepository;
import org.ichat.backend.services.social.IPostService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DataLoader implements ApplicationRunner {
    private final IPostService postService;
    private final AdminRepository adminRepo;
    private final JobseekerRepository jobseekerRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder encoder;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createRoles();
        createAdminIfNotExists();
        createTemplatePosts();
        createJobseekers();
    }

    private void createRoles() {
        List<Roles> roles = roleRepo.findAll();
        if (roles.isEmpty()) {
            roleRepo.saveAll(List.of(new Roles(1, RoleType.JOBSEEKER),
                    new Roles(2, RoleType.ADMIN), new Roles(3, RoleType.COMPANY)));
            log.info("Roles created successfully");
        }
    }

    private void createAdminIfNotExists() {
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
            log.info("Admin not found, created a new one with default credentials");
        }
    }

    private void createJobseekers() {
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
            profile2.setUser(jobseeker2);
            jobseeker2.setProfile(profile2);
            jobseeker2.setFirst_name("Jane");
            jobseeker2.setLast_name("Doe");
            jobseeker2.setEmail("receiver@example.com");
            jobseeker2.setUsername("jobseeker2");
            jobseeker2.setPassword(encoder.encode("12345678"));

            jobseekerRepo.saveAll(List.of(jobseeker1, jobseeker2));
            log.info("No jobseekers found, created some.");
        }
    }

    private void createTemplatePosts() throws IOException {
        boolean haveNoPosts = postService.getLatestPosts(Pageable.ofSize(1)).isEmpty();
        if (haveNoPosts) {
            Admin admin = adminRepo.findById(1L).orElseThrow();
            createPostFromFile("classpath:static/posts-examples/post1.txt", "posts/reusability.jpg", admin);
            createPostFromFile("classpath:static/posts-examples/post2.txt", "posts/image_loading.jpg", admin);
            createPostFromFile("classpath:static/posts-examples/post3.txt", null, admin);
            createPostFromFile("classpath:static/posts-examples/post4.txt", "posts/css.jpg", admin);
            createPostFromFile("classpath:static/posts-examples/post5.txt", "posts/backend.jpg", admin);
            createPostFromFile("classpath:static/posts-examples/post6.txt", null, admin);
            log.info("No posts found, created some templates");
        }
    }

    protected void createPostFromFile(String filePath, String image, User author) throws IOException {
        var resource = resourceLoader.getResource(filePath);
        String content = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        postService.createPost(author, image, content);
    }
}
