package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.jobs.Job;

import java.util.List;
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
    @NotBlank(message = "Company name is required")
    String company_name;

    @Column(nullable = false)
    @NotBlank(message = "SIREN is required")
    String siren;

    String description;

    @Column(nullable = false)
    @NotBlank(message = "Headquarters is required")
    String headquarters;

    @Column(nullable = false)
    @NotBlank(message = "Founded date is required")
    String foundedDate;

    @Column(nullable = false)
    @NotBlank(message = "Sector is required")
    String sector;

    String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Job> postedJobs;
}
