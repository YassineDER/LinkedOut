package org.ichat.backend.model.util;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCredentials {
    @NotEmpty(message = "email is mandatory")
    String email;
    @NotEmpty(message = "password is mandatory")
    String password;
}
