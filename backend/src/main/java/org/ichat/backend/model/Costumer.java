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
public class Costumer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String photoUrl;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 45)
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<Invoice> customerInvoices;

}
