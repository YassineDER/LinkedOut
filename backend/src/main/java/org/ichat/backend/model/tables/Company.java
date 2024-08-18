package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.jobs.Job;

import java.util.Set;

/**
 * Company entity is a subclass of User entity.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue("C")
public class Company extends User {
    @Column(nullable = false)
    @NotEmpty(message = "Company name is required")
    String company_name;

    @Column(nullable = false)
    @NotEmpty(message = "SIREN is required")
    String siren;

    String description;

    @Column(nullable = false)
    @NotEmpty(message = "Headquarters is required")
    String headquarters;

    @Column(nullable = false)
    @NotEmpty(message = "Founded date is required")
    String foundedDate;

    @Column(nullable = false)
    @NotEmpty(message = "Sector is required")
    String sector;

    String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Job> postedJobs;
}
