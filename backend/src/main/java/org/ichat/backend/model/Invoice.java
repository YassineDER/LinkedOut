package org.ichat.backend.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoice_id;

    @Column(nullable = false)
    @NotNull(message = "Invoice date cannot be null")
    private OffsetDateTime date;

    @Column(nullable = false, length = 45)
    @NotEmpty(message = "Invoice number cannot be empty")
    private String number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Costumer customer;

    @ManyToMany
    @JoinTable(
            name = "invoice_management",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services;

}
