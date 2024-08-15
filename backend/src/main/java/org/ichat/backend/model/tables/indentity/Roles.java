package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.ichat.backend.model.util.RoleType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public String getName() {
        return name.toString();
    }

}
