package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;

    @Column(nullable = false)
    @NotBlank(message = "Message content is mandatory")
    private String content;

    @Column(nullable = false)
    private LocalDateTime message_date = LocalDateTime.now();

    @JoinColumn(nullable = false, name = "conversation_id", referencedColumnName = "conversation_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private Converstation conversation;

}
