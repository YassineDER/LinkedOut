package org.ichat.backend.repository;

import org.ichat.backend.model.AccountReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AccountResetRepository extends JpaRepository<AccountReset, Integer> {
    @Modifying
    @Query(value = "DELETE FROM AccountReset a WHERE a.expiresAt < :thresholdDate")
    void deleteAllExpiredSince(Date thresholdDate);
}
