package org.ichat.backend.model.tables.messaging;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
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

}
