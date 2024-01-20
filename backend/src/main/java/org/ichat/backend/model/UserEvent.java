package org.ichat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userEventId;

    @Column
    private OffsetDateTime createdDate;

    @Column
    private String device;

    @Column
    private String ipAddress;

    @Column(nullable = false)
    private String type;

    @Column
    private String browser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
