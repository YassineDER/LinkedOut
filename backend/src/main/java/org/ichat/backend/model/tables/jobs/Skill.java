package org.ichat.backend.model.tables.jobs;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long skill_id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Skill name is required")
    String name;

}
