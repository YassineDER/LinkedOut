package org.ichat.backend.service.social.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Comment;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.PostRequest;
import org.ichat.backend.model.util.social.Reaction;
import org.ichat.backend.repository.PostRepository;
import org.ichat.backend.service.social.IPostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository repo;

    @Override
    public Post createPost(PostRequest req) {
        if (req.getDescription().length() > 1000)
            throw new IllegalArgumentException("Description too long");

        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var P = new Post();
        P.setDescription(req.getDescription());
        P.setImage_url(req.getImage_url());
        P.setUser(creator);

        return repo.save(P);
    }

    @Override
    public List<Post> getLatestPosts() {
        // TODO: All latest posts, pagination and sorting included
        return List.of();
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        // TODO: All posts by a user, pagination and sorting included
        return List.of();
    }

    @Override
    public Post getPostById(Long postId) {
        return repo.findById(postId).orElse(null);
    }

    @Override
    public void reactToPost(Long postId, Reaction reaction) {
        var post = repo.findById(postId).orElseThrow(() -> new NoSuchElementException("Post not found"));
        if (reaction == Reaction.LIKE)
            post.like();
        else post.unlike();

        repo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        repo.deleteById(postId);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        var post = repo.findById(postId).orElseThrow(() -> new NoSuchElementException("Post not found"));
        return post.getComments().stream().toList();
    }
}
