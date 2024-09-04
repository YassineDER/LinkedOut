package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 1500)
    String description;

    @Column(name = "image_name")
    String imageName;

    @CreatedDate
    @NotNull(message = "Created date is required")
    LocalDateTime created = LocalDateTime.now();

    int likes = 0;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @JsonIgnoreProperties({"posts", "connections", "comments", "profile_views", "bio", "banner_name"})
    Profile profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"post"})
    private List<Comment> comments;

    public void like() {
        this.likes++;
    }

    public void unlike() {
        this.likes--;
    }

}
