package org.ichat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Full name is required")
    private String fullName;

    @Column(nullable = false)
    @NotEmpty(message = "Photo URL is required")
    private String photoUrl;

    @Column(nullable = false, length = 45)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false, length = 15)
    @NotEmpty(message = "Phone number is required")
    private String phone;

    @Column(nullable = false, length = 45)
    @NotEmpty(message = "Address is required")
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<Invoice> customerInvoices;

}
