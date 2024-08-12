package org.ichat.backend.model.tables.indentity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.ichat.backend.model.util.RoleType;

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
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public String getName() {
        return name.toString();
    }

}
