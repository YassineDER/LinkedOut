package org.ichat.backend.repository;

import org.ichat.backend.model.TwoFactorAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TwoFactorAuthenticationRepository extends JpaRepository<TwoFactorAuthentication, Integer> {
}
