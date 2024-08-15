package org.ichat.backend.services.social;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Comment;
import org.ichat.backend.model.tables.social.Post;
import org.ichat.backend.model.util.social.PostRequestDTO;
import org.ichat.backend.model.util.social.Reaction;

import java.util.List;

public interface IPostService {
    Post createPost(User creator, PostRequestDTO req);
    List<Post> getLatestPosts();
    List<Post> getPostsByUser(Long userId);

    /**
     * Get post by id
     * @param postId id of the post
     * @return Post object, null if not found
     */
    Post getPostById(Long postId);

    void reactToPost(Long postId, Reaction reaction);
    void deletePost(Long postId);
    List<Comment> getComments(Long postId);
}
