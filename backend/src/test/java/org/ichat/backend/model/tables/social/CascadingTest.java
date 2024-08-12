package org.ichat.backend.model.tables.social;

import jakarta.persistence.EntityManager;
import org.ichat.backend.repository.CommentRepository;
import org.ichat.backend.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(showSql = false)
class CascadingTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager manager;

    Post post;
    Comment comment;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
        commentRepository.deleteAll();

        post = new Post();
        comment = new Comment();
        comment.setContent("Test comment");
        post.setDescription("Test post");
    }

    @Test
    public void whenPostIsRemovedThenCommentsAreAlsoRemoved() {
        post.setComments(Set.of(comment));

        postRepository.save(post);
        postRepository.flush();

        // When
        postRepository.delete(post);
        postRepository.flush();

        // Then
        assertEquals(0, postRepository.count());
        assertEquals(0, commentRepository.count());
    }

    @Test
    public void whenCommentIsRemovedThenPostIsNotRemoved() {
        comment.setPost(post);

        postRepository.save(post);
        postRepository.flush();  // Ensure the entities are persisted in the database

        // When
        commentRepository.delete(comment);
        commentRepository.flush();

        // Then
        assertEquals(1, postRepository.count());
        assertEquals(0, commentRepository.count());
    }
}
