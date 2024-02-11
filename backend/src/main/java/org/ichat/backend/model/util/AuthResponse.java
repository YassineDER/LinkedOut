package org.ichat.backend.model.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Don't include null fields in response
public class AuthResponse {
    private final String response;
    private boolean must_verify_mfa;
    private String qr_image;
}
