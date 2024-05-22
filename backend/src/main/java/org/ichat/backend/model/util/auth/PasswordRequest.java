package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {
    @NotEmpty(message = "Received code is missing")
    String received_code;
    @NotEmpty(message = "The new password is missing")
    String password;
}
