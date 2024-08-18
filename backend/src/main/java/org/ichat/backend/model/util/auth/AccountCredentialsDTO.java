package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the authentication process.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentialsDTO {
    @NotEmpty(message = "email is mandatory")
    String email;
    @NotEmpty(message = "password is mandatory")
    String password;
    String code;
}
