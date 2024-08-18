package org.ichat.backend.model.util.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * AuthResponseDTO is a data transfer object that is used to send a response to the client after an authentication request.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Don't include null fields in response
public class AuthResponseDTO {
    private final String response;
    private boolean must_verify_mfa = false;
    private String qr_image;

    public AuthResponseDTO(String response, boolean must_verify_mfa) {
        this.response = response;
        this.must_verify_mfa = must_verify_mfa;
    }
}
