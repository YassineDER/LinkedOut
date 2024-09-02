package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisterCompanyRequestDTO is a data transfer object that is used to send a request to the server to register a company.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCompanyRequestDTO {
    @NotBlank(message = "SIREN is mandatory")
    @Size(min = 9, max = 9, message = "SIREN must be 9 characters")
    private String siren;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    boolean debug = false;

}
