package org.ichat.backend.model.tables.social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Connection entity class. Represents a connection between two profiles.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long connection_id;

    LocalDateTime connection_date = LocalDateTime.now();

    @ManyToOne(targetEntity = Profile.class, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile1_id", referencedColumnName = "profile_id")
    Profile profile1;

    @ManyToOne(targetEntity = Profile.class, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile2_id", referencedColumnName = "profile_id")
    Profile profile2;
}
