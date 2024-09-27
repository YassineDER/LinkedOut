package org.ichat.backend.repository;

import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.util.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(RoleType type);

}
