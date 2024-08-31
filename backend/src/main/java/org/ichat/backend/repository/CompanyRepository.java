package org.ichat.backend.repository;

import org.ichat.backend.model.tables.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmail(String email);
    Optional<Company> findByUsername(String username);

    boolean existsBySiren(String siren);
}
