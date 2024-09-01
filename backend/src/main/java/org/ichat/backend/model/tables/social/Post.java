package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Post entity class, represents a post in the social media platform.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long post_id;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false)
    String description;

    @Column(nullable = true, name = "image_name")
    String imageName;

    LocalDateTime created = LocalDateTime.now();

    int likes = 0;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @JsonIncludeProperties("profile_id, user")
    Profile profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("post")
    private List<Comment> comments = List.of();

    public void like() {
        this.likes++;
    }

    public void unlike() {
        this.likes--;
    }

}
