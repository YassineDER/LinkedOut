package org.ichat.backend.repository;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}
