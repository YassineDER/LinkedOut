package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.User;

/**
 * This class represents the AccountReset table in the database.
 * It is used to store the token and expiration date for a password reset request.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_reset_id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Token code is required")
    private String token;

    @Column(nullable = false)
    @NotNull(message = "Expiration date is required")
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    public void resetUserPassword(String encodedPassword) {
        this.user.setPassword(encodedPassword);
    }
}
