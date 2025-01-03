package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.messaging.Converstation;

import java.time.LocalDateTime;

/**
 * Connection entity class. Represents a connection between two profiles.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"sender_id", "receiver_id"}))
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long connection_id;

    @Column(nullable = false)
    LocalDateTime connection_date = LocalDateTime.now();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "profile_id")
    @JsonBackReference
    Profile sender;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id", referencedColumnName = "profile_id")
    @JsonBackReference
    Profile receiver;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnoreProperties({"messages"})
    private Converstation conversation;
}
