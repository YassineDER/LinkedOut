package org.ichat.backend.repository;

import org.ichat.backend.model.AccountReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface AccountResetRepository extends JpaRepository<AccountReset, Integer> {
    void deleteByExpiresAtBefore(OffsetDateTime threshold);
}
