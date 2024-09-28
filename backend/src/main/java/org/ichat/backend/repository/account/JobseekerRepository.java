package org.ichat.backend.repository.account;

import org.ichat.backend.model.tables.Jobseeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobseekerRepository extends JpaRepository<Jobseeker, Long>{
    Optional<Jobseeker> findByEmail(String email);
}
