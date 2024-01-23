package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TwoFactorAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer a2fa_id;

    @Column(nullable = false, unique = true, length = 10)
    @NotEmpty(message = "Code must be valid")
    private String code;

    @Column(nullable = false)
    @NotNull(message = "Expire date must be valid")
    private OffsetDateTime expiresAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
