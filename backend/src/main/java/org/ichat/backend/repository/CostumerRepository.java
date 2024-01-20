package org.ichat.backend.repository;

import org.ichat.backend.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}
