package org.ichat.backend.model.util.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecaptchaResponse {
    private boolean success;
    private String challenge_ts, hostname, action;
    private float score;
    private String[] error_codes;
}
