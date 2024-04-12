package org.ichat.backend.repository;

import jakarta.transaction.Transactional;
import org.ichat.backend.model.tables.AccountReset;
import org.ichat.backend.model.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AccountResetRepository extends JpaRepository<AccountReset, Integer> {
    @Modifying
    @Transactional
    @Query("delete from AccountReset a where a.expiresAt < :threshold")
    void deleteByExpiresAtBefore(OffsetDateTime threshold);

    void deleteByUser(User user);

    @Modifying
    @Transactional
    @Query("delete from AccountReset a where a.token = :token")
    void deleteByToken(String token);
    Optional<AccountReset> findByToken(String token);
}
