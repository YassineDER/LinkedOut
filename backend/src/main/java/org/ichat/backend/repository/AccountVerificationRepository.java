package org.ichat.backend.repository;


import org.ichat.backend.model.AccountVerification;
import org.ichat.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Integer> {
    // Delete all account verifications that have expired before the provided date
    void deleteByExpiresAtBefore(OffsetDateTime expiresAt);

    void deleteByUser(User user);

    Optional<AccountVerification> findByToken(String token);

}
