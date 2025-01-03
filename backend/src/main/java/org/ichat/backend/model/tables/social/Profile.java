package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichat.backend.model.tables.User;

import java.util.List;

/**
 * Profile class is an abstract class that represents a profile of a user in the social network.
 * It is the parent class of the JobseekerProfile and CompanyStaffProfile classes.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "profile_type")
@DiscriminatorValue("PROFILE")
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    Long profileId;

    @Column(nullable = false)
    @Length(min = 3, max = 150, message = "Bio must be between 3 and 350 characters")
    @NotBlank(message = "Bio is required")
    String bio = "Bonjour, je suis nouveau sur LinkedOut!";

    @Column(nullable = false)
    @NotBlank(message = "Banner is required")
    String banner_name = "profile/banners/default_banner.png";

    @Column(nullable = false)
    Integer profile_views = 0;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "first_name", "last_name", "title", "sector", "imageName", "role"})
    User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profile"})
    List<Post> posts;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Connection> sentConnections;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Connection> receivedConnections;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"author"})
    List<Comment> comments;

    @JsonProperty("connections")
    public int getNumberOfConnections() {
        return sentConnections.size() + receivedConnections.size();
    }
}
