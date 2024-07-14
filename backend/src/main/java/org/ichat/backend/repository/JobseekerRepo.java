package org.ichat.backend.repository;

import org.ichat.backend.model.tables.Jobseeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobseekerRepo extends JpaRepository<Jobseeker, Long>{
    Optional<Jobseeker> findByEmail(String email);
    Optional<Jobseeker> findByUsername(String username);

    @Override
    Page<Jobseeker> findAll(Pageable pageable);
}
