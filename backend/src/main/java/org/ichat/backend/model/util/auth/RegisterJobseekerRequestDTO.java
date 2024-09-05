package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisterJobseekerRequestDTO is a data transfer object that is used to send a request to the server to register a jobseeker.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobseekerRequestDTO {
    @NotBlank(message = "First name is required")
    String first_name;
    @NotBlank(message = "Last name is required")
    String last_name;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    private String image;
}