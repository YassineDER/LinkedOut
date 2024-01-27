package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Username is invalid")
    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @NotEmpty(message = "Password is required")
    String password;

    @NotEmpty(message = "Address is required")
    @Column(nullable = false)
    String address;

    @Column
    String phone;

    @NotEmpty(message = "Image is required")
    @Column(nullable = false)
    String image_url;

    @Column(nullable = false)
    Boolean enabled = false;

    @Column(nullable = false)
    Boolean using_mfa = false;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<AccountReset> userAccountResets;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private Set<AccountVerification> userAccountVerifications;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<TwoFactorAuthentication> userTwoFactorAuthentications;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<Events> userEvents;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    @JsonIgnoreProperties("role_id")
    Set<Roles> user_roles;
}
