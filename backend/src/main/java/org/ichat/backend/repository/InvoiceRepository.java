package org.ichat.backend.repository;

import org.ichat.backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
