package org.ichat.backend.repository;

import org.ichat.backend.model.tables.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByUsername(String username);
}

