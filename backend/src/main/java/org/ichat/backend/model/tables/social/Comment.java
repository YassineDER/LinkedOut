package org.ichat.backend.model.tables.social;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long comment_id;

    @NotEmpty(message = "Comment content is required")
    @Column(nullable = false)
    String content;

    LocalDateTime created_at = LocalDateTime.now();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    Post post;

}
