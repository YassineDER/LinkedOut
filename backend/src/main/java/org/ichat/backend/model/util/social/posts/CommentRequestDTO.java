package org.ichat.backend.model.util.social.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDTO {
    @NotNull(message = "Post ID is required")
    Long postId;
    @NotBlank(message = "Content is required")
    String content;
}
