package org.ichat.backend.repository;

import org.ichat.backend.model.AccountReset;
import org.ichat.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AccountResetRepository extends JpaRepository<AccountReset, Integer> {
    void deleteByExpiresAtBefore(OffsetDateTime threshold);
    void deleteByUser(User user);

    Optional<AccountReset> findByToken(String token);
}
