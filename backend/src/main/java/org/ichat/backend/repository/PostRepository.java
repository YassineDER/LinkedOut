package org.ichat.backend.repository;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
}
