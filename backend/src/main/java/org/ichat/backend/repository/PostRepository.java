package org.ichat.backend.repository;

import org.ichat.backend.model.tables.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedDateDesc();

    @Query("SELECT p FROM Post p WHERE p.user.user_id = ?1 ORDER BY p.created DESC")
    List<Post> findAllByUser(Long userId);
}
