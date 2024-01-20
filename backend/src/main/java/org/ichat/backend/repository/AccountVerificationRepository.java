package org.ichat.backend.repository;

import org.ichat.backend.model.AccountVerification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Integer> {
}
