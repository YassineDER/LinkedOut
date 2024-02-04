package org.ichat.backend.repository;


import org.ichat.backend.model.AccountVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Integer> {
    void deleteAllByExpiresAtBefore(OffsetDateTime expiresAt);

    Optional<AccountVerification> findByToken(String token);
}
