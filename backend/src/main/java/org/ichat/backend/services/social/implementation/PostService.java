package org.ichat.backend.services.social.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Comment;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.posts.Reaction;
import org.ichat.backend.repository.social.CommentRepository;
import org.ichat.backend.repository.social.PostRepository;
import org.ichat.backend.services.shared.IStorageService;
import org.ichat.backend.services.social.IPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository postRepo;
    private final CommentRepository commentRepo;
    private final IStorageService storageService;

    private static final String ALLOWED_IMAGE_EXTENSIONS = "jpeg|png|jpg";

    @Override
    public Post createPost(User creator, String image, String description) {
        Post post = new Post();
        var profile = creator.getProfile();
        post.setProfile(profile);
        post.setDescription(description);

        if (image != null) {
            String image_extension = ".jpg";
            String image_path = "posts/post-" + profile.getProfileId() + "-" + System.currentTimeMillis() + image_extension;

            try {
                byte[] decoded_image = Base64.getDecoder().decode(image);
                storageService.uploadBase64Image(decoded_image, image_path);
                post.setImageName(image_path);
            } catch (IllegalArgumentException e) {
                post.setImageName(image);
            }
        }

        var creator_posts = creator.getProfile().getPosts();
        creator_posts.add(post);
        var created_post = postRepo.save(post);
        postRepo.flush();
        return created_post;
    }

    @Override
    public Page<Post> getLatestPosts(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        // TODO: All posts by a user, pagination and sorting included
        return List.of();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    @Override
    public Comment addComment(Long postId, User commenter, String content) {
        var post = postRepo.findById(postId).orElseThrow(() -> new NoSuchElementException("Post not found"));
        var comment = new Comment();
        comment.setPost(post);
        comment.setAuthor(commenter.getProfile());
        comment.setContent(content);

        return commentRepo.save(comment);
    }

    @Override
    public void reactToPost(Long postId, User reactor, String reaction) {
        var post = postRepo.findById(postId).orElseThrow(() -> new NoSuchElementException("Post not found"));
        if (reaction.equals(Reaction.LIKE.name()))
            post.like();
        else post.unlike();

        postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public boolean existsByImage(String name) {
        return postRepo.existsByImageName(name);
    }
}
