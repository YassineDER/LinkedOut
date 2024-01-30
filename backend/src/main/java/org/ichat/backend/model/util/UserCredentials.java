package org.ichat.backend.model.util;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {

    @NotEmpty(message = "email is mandatory")
    String email;
    @NotEmpty(message = "password is mandatory")
    String password;
}
