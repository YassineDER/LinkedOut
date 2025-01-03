package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Comment;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.posts.CommentRequestDTO;
import org.ichat.backend.model.util.social.posts.PostRequestDTO;
import org.ichat.backend.services.social.IPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for social media related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final IPostService postService;

    /**
     * Endpoint for creating a post
     * @param me the user creating the post
     * @param req the request body containing the post content
     * @return the created post
     */
    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(User me, @Valid @RequestBody PostRequestDTO req) {
        Post post = postService.createPost(me, req.getImage_b64(), req.getDescription());
        return ResponseEntity.ok(post);
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(User me, @Valid @RequestBody CommentRequestDTO req) {
        Comment comment = postService.addComment(req.getPostId(), me, req.getContent());
        return ResponseEntity.ok(comment);
    }


    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return postService.getLatestPosts(pageable);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}/react")
    public void reactToPost(User me, @PathVariable Long postId, @RequestParam String reaction) {
        postService.reactToPost(postId, me, reaction);
    }

}
