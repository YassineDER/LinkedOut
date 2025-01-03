package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PasswordRequestDTO is a data transfer object that is used to send a request to the server to change the password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequestDTO {
    @NotBlank(message = "Received code is missing")
    String received_code;
    @NotBlank(message = "The new password is missing")
    String password;
    @NotBlank(message = "The new password confirmation is missing")
    String password_confirmation;
}
