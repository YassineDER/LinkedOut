package org.ichat.backend.core;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.account.AdminRepository;
import org.ichat.backend.services.social.IPostService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostsLoader implements ApplicationRunner {
    private final IPostService postService;
    private final AdminRepository adminRepo;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createTemplatePosts();
    }

    private void createTemplatePosts() throws IOException {
        boolean haveNoPosts = postService.getLatestPosts(Pageable.unpaged()).isEmpty();
        if (haveNoPosts) {
            Admin admin = adminRepo.findByUsername("admin").orElseThrow();
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
