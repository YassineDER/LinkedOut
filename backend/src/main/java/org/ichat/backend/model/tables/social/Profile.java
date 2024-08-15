package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

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
    String banner_url = "https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/images/o/default_banner.png";

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Post> posts;

    @Column(nullable = false)
    Integer profile_views = 0;

    @OneToMany(mappedBy = "profile1", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Connection> connections;

    @JsonProperty("connections")
    public int getNumberOfConnections() {
        return connections.size();
    }
}
