package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor // constructor with fields that are marked as @NonNull
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer role_id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Role name cannot be empty")
    @NonNull
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "user_roles", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> assigned_users;

}
