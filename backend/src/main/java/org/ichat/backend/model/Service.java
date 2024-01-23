package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;

    @Column(nullable = false, length = 75)
    @NotEmpty(message = "Service name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Service price cannot be null")
    private Double price;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private Set<Invoice> invoices;

}
