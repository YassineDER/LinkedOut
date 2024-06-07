package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.User;

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

    @JsonIncludeProperties({"user_id", "image_url", "username", "company_name", "first_name", "last_name", "title"})
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;


    public void like() {
        this.likes++;
    }

    public void unlike() {
        this.likes--;
    }

}
