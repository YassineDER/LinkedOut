package org.ichat.backend.model.tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * Admin class is a subclass of User class. It is used to store the information of an admin user.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@DiscriminatorValue("A")
public class Admin extends User {
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    String first_name;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    String last_name;

    @Column(nullable = false)
    @NotBlank(message = "Phone number is required")
    String phone;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    String title;

}
