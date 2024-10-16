package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ichat.backend.model.tables.User;

/**
 * This class represents the account verification table in the database.
 * An account verification is created when a user registers an account and is used to verify the user's email address.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_verification_id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Token cannot be empty")
    private String token;

    @Column(nullable = false)
    @NotNull(message = "Expiration date cannot be null")
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
