package org.ichat.backend.model;

import jakarta.annotation.Nullable;
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
    @NotEmpty(message = "First name is required")
    String first_name;

    @Column(nullable = false)
    @NotEmpty(message = "Last name is required")
    String last_name;

    @Column(nullable = false, unique = true)
    @Email(message = "Email is invalid")
    String email;

    @NotEmpty(message = "Address is required")
    @Column(nullable = false)
    String address;

    @NotEmpty(message = "Phone is required")
    @Column(nullable = false)
    String phone;

    @NotEmpty(message = "Image is required")
    @Column(nullable = false)
    String image_url;

    @Column(nullable = false)
    @NotNull
    Boolean enabled = true;

    @Column(nullable = false)
    @NotNull
    Boolean using_mfa = false;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AccountReset> userAccountResets;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<AccountVerification> userAccountVerifications;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<TwoFactorAuthentication> userTwoFactorAuthentications;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserEvent> userUserEvents;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userUserRoles;
}
