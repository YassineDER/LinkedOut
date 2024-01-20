package org.ichat.backend.repository;

import org.ichat.backend.model.InvoiceManagement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceManagementRepository extends JpaRepository<InvoiceManagement, Integer> {
}
