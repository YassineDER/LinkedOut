package org.ichat.backend.model.tables;

import jakarta.persistence.*;
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
    @NotEmpty(message = "First name is required")
    String first_name;

    @Column(nullable = false)
    @NotEmpty(message = "Last name is required")
    String last_name;

    @Column(nullable = false)
    @NotEmpty(message = "Phone number is required")
    String phone;

    @Column(nullable = false)
    @NotEmpty(message = "Title is required")
    String admin_title;

}
