package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCompanyRequest {
    @NotEmpty(message = "SIREN is mandatory")
    @Size(min = 9, max = 9, message = "SIREN must be 9 characters")
    private String siren;
    @NotEmpty(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "username is mandatory")
    private String username;
    @NotEmpty(message = "password is mandatory")
    private String password;
    @NotEmpty(message = "captcha is mandatory")
    private String captcha;

}
