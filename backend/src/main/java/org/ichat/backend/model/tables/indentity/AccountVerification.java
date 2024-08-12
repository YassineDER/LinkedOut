package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ichat.backend.model.tables.User;


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
    @NotEmpty
    private String token;

    @Column(nullable = false)
    @NotNull
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
