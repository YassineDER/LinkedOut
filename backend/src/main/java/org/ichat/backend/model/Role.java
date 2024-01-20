package org.ichat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

import lombok.*;


@Entity
@Table(name = "\"Role\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String permissions;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> roleUserRoles;

}
