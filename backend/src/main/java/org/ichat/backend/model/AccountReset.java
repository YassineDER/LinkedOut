package org.ichat.backend.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
