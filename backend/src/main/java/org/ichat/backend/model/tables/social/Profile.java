package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichat.backend.model.tables.User;

import java.util.List;
import java.util.Set;

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
    Long profile_id;

    @Column(nullable = false)
    @Length(min = 3, max = 150, message = "Bio must be between 3 and 350 characters")
    String bio = "Bonjour, je suis nouveau sur LinkedOut!";

    @Column(nullable = false)
    String banner_name = "profile/banners/default_banner.png";

    @Column(nullable = false)
    Integer profile_views = 0;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "first_name", "last_name", "username", "imageName"})
    User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("profile")
    private List<Post> posts;

    @OneToMany(mappedBy = "profile1", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Connection> connections;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("author")
    private List<Comment> comments;

    @JsonProperty("connections")
    public int getNumberOfConnections() {
        return connections.size();
    }
}
