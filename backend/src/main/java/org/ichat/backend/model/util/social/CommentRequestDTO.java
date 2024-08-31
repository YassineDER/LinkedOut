package org.ichat.backend.model.util.social;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDTO {
    @NotNull(message = "Post ID is required")
    Long postId;
    @NotEmpty(message = "Content is required")
    String content;
}
