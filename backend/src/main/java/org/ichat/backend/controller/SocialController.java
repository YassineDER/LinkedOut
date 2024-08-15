package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.PostRequestDTO;
import org.ichat.backend.services.social.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/social")
public class SocialController {
    private final IPostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public ResponseEntity<Post> addPost(User me, @Valid @RequestBody PostRequestDTO req) {
        Post post = postService.createPost(me, req);
        return ResponseEntity.ok(post);
    }

}
