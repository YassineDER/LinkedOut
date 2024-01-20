package org.ichat.backend.repository;

import org.ichat.backend.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {
}
