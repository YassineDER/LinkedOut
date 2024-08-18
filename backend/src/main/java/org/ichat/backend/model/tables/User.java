package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.ichat.backend.model.tables.indentity.AccountReset;
import org.ichat.backend.model.tables.indentity.AccountVerification;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.social.Profile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The User class is an abstract class that represents the user entity in the database.
 * It is an abstract class because it is the parent class of the Admin, JobSeeker, and Company classes.
 * @apiNote This class is so important. Try not to break it.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "user_id")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, name = "user_type")
@DiscriminatorValue("U")
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long user_id;

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

    String mfa_secret;

    String image_url = "https://img.icons8.com/pastel-glyph/64/000000/user-male-circle.png";

    @Column(nullable = false)
    Boolean enabled = false;

    @Column(nullable = false)
    Boolean using_mfa = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<AccountReset> userAccountResets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<AccountVerification> userAccountVerifications;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonIgnoreProperties("role_id")
    Roles role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Profile profile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(Set.of(role)).stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public void activateMFA(String secret) {
        this.using_mfa = true;
        this.mfa_secret = secret;
    }

    public void deactivateMFA() {
        this.using_mfa = false;
        this.mfa_secret = null;
    }
}
