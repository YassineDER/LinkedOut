package org.ichat.backend.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Events {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    @Column(nullable = false)
    private OffsetDateTime createdDate = OffsetDateTime.now();

    @Column
    private String device;

    @Column
    private String ipAddress;

    @Column(nullable = false)
    @NotEmpty(message = "Event type cannot be empty")
    private String type;

    @Column
    private String browser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
