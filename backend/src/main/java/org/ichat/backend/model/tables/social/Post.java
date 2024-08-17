package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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

    String image_url;

    LocalDateTime created = LocalDateTime.now();

    int likes = 0;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @JsonIgnore
    Profile profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    public void like() {
        this.likes++;
    }

    public void unlike() {
        this.likes--;
    }

}
