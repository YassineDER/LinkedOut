package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Role name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Role permissions cannot be empty")
    private String permissions;

    @JsonIgnore
    @ManyToMany(mappedBy = "user_roles", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> assigned_users;

}
