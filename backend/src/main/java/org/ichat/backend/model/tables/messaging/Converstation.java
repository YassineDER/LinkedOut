package org.ichat.backend.model.tables.messaging;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.social.Connection;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Converstation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long conversation_id;

    @CreatedDate
    private Timestamp time;

    @LastModifiedDate
    private Timestamp last_modified;

    @Column(length = -1)
    private String content;

    @Column(length = -1)
    private String delivery_status;

    @OneToOne(mappedBy = "conversation", cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id", referencedColumnName = "connection_id")
    @JsonIncludeProperties({"connection_id", "connection_date", "sender", "receiver"})
    private Connection connection;
}
