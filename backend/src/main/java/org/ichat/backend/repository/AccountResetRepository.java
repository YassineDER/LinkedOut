package org.ichat.backend.repository;

import org.ichat.backend.model.AccountReset;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountResetRepository extends JpaRepository<AccountReset, Integer> {
}
