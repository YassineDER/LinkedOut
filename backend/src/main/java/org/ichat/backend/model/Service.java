package org.ichat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @Column(nullable = false, length = 75)
    private String name;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "service")
    private Set<InvoiceManagement> serviceInvoiceManagements;

}
