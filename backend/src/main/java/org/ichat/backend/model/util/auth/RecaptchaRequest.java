package org.ichat.backend.model.util.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecaptchaRequest {
    private String secret;
    private String response;
}
