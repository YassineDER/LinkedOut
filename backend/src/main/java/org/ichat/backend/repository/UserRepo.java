package org.ichat.backend.repository;

import org.ichat.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
