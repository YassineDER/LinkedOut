package org.ichat.backend.model.util.social;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequest {
    @NotEmpty(message = "Post title is required")
    private String description;
    private String image_url;
}
