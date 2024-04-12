package org.ichat.backend.model.tables;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_verification_id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String token;

    @Column(nullable = false)
    @NotNull
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    public void verifyUser() {
        this.user.setEnabled(true);
    }

}
