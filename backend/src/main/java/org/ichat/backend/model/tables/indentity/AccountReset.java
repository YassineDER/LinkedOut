package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.User;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReset {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_reset_id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String token;

    @Column(nullable = false)
    @NotNull
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public void resetUserPassword(String encodedPassword) {
        this.user.setPassword(encodedPassword);
    }
}
