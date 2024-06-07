package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.assemblers.PostModelAssembler;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.PostRequest;
import org.ichat.backend.service.social.IPostService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    private final PostModelAssembler assembler;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest req) {
        var post = postService.createPost(req);
        EntityModel<Post> postModel = assembler.toModel(post);
        return ResponseEntity.created(postModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(postModel);
    }

}
