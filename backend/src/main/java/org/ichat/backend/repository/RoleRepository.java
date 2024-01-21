package org.ichat.backend.repository;

import org.ichat.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
