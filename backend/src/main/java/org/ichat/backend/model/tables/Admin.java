package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.social.SocialProfile;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private SocialProfile profile;

}
