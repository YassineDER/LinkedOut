package org.ichat.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    Long user_id;

    @Column(nullable = false)
    String first_name;

    @Column(nullable = false)
    String last_name;

    @Column(nullable = false, unique = true)
    @Email(message = "Email is invalid")
    String email;

    @NotEmpty(message = "Address is required")
    String address;

    @NotEmpty(message = "Phone is required")
    String phone;

    @NotEmpty(message = "Image is required")
    String image_url;

    @Column(nullable = false)
    Boolean enabled;

    @Column(nullable = false)
    Boolean using_mfa;

    @Column
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.)
    private Set<AccountReset> userAccountResets;

    @OneToMany(mappedBy = "user")
    private Set<AccountVerification> userAccountVerifications;

    @OneToMany(mappedBy = "user")
    private Set<TwoFactorAuthentication> userTwoFactorAuthentications;

    @OneToMany(mappedBy = "user")
    private Set<UserEvent> userUserEvents;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userUserRoles;
}
