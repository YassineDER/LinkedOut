package org.ichat.backend.model.util.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobseekerRequest {
    @NotEmpty(message = "First name is required")
    String first_name;
    @NotEmpty(message = "Last name is required")
    String last_name;
    @NotEmpty(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "username is mandatory")
    private String username;
    @NotEmpty(message = "password is mandatory")
    private String password;
    private String image_url = "https://img.icons8.com/pastel-glyph/64/000000/user-male-circle.png";
    @NotEmpty(message = "captcha is mandatory")
    private String captcha;
    boolean debug = false;

}
