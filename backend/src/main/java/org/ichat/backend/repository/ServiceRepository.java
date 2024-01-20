package org.ichat.backend.repository;

import org.ichat.backend.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
