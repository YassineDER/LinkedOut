package org.ichat.backend.model.util.social.posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostRequestDTO is a data transfer object that is used to send a request to the server to create a new post in social media.
 */
@Data
@NoArgsConstructor
public class PostRequestDTO {
    @NotBlank(message = "Post description is required")
    private String description;
    private String image_b64;
}
