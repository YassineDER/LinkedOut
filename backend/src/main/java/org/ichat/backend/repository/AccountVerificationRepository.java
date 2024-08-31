package org.ichat.backend.repository;


import jakarta.transaction.Transactional;
import org.ichat.backend.model.tables.indentity.AccountVerification;
import org.ichat.backend.model.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {
    // Delete all account verifications that have expired before the provided date
    @Modifying
    @Transactional
    @Query("DELETE FROM AccountVerification a WHERE a.expiresAt < :expiresAt")
    void deleteByExpiresAtBefore(OffsetDateTime expiresAt);

    void deleteByUser(User user);

    Optional<AccountVerification> findByToken(String token);
}
