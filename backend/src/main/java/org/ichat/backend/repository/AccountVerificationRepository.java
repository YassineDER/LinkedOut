package org.ichat.backend.repository;

import org.ichat.backend.model.AccountVerification;
import org.ichat.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Integer> {
    @Modifying
    @Query("DELETE FROM AccountVerification e WHERE e.expiresAt < :thresholdDate")
    void deleteAllExpiredSince(Date thresholdDate);

    Optional<AccountVerification> findByToken(String token);
}
