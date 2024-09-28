package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Converstation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long conversation_id;

    @OneToOne(mappedBy = "conversation", cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id", referencedColumnName = "connection_id")
    @JsonIncludeProperties({"connection_id", "connection_date", "sender", "receiver"})
    private Connection connection;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<ChatMessage> messages;
}
