package org.ichat.backend.repository;

import org.ichat.backend.model.tables.social.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByImageName(String imageName);
}
