package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials {
    @NotEmpty(message = "email is mandatory")
    String email;
    @NotEmpty(message = "password is mandatory")
    String password;
    String code;
}
