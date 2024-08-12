package org.ichat.backend.model.tables.social;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichat.backend.model.tables.User;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "profile_type")
@DiscriminatorValue("P")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long profile_id;

    @Column(nullable = false)
    @Length(min = 3, max = 150, message = "Bio must be between 3 and 350 characters")
    String bio = "Bonjour, je suis nouveau sur LinkedOut!";

    @Column(nullable = false)
    String banner_url = "https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/images/o/default_banner.png";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Post> posts;
}
