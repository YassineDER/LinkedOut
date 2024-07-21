package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.PostRequestDTO;
import org.ichat.backend.service.social.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/social")
public class SocialController {
    private final IPostService postService;

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequestDTO req) {
        Post post = postService.createPost(req);
        return ResponseEntity.ok(post);
    }

}
