package org.ichat.backend.repository;

import org.ichat.backend.model.TwoFactorAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;

@Repository
public interface TwoFactorAuthenticationRepository extends JpaRepository<TwoFactorAuthentication, Integer> {
    void deleteAllByExpiresAtBefore(OffsetDateTime thresholdDate);
}
