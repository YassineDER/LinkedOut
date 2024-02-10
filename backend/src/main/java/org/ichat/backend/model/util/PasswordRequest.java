package org.ichat.backend.model.util;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {
    @NotEmpty(message = "Something missing")
    String received_code;
    @NotEmpty(message = "Something missing ")
    String password;
}
