package org.ichat.backend.services.social;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Comment;
import org.ichat.backend.model.tables.social.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Post createPost(User creator, String image, String description);
    Page<Post> getLatestPosts(Pageable pageable);
    List<Post> getPostsByUser(Long userId);

    /**
     * Get post by id
     * @param postId id of the post
     * @return Post object, null if not found
     */
    Post getPostById(Long postId);

    Comment addComment(Long postId, User commenter, String content);

    void reactToPost(Long postId, User reactor, String reaction);
    void deletePost(Long postId);
}
