package org.ichat.backend.repository;

import org.ichat.backend.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
