package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.job.JobType;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long job_id;

    @NotEmpty(message = "Title is required")
    @Column(nullable = false)
    String title;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false)
    String description;

    @NotEmpty(message = "Location is required")
    @Column(nullable = false)
    String location; // autocomplete with google maps api

    @NotEmpty(message = "Type is required")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    JobType type;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "image_url", "company_name"})
    Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIncludeProperties({"name"})
    private Set<Skill> requiredSkills;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JobApplication> jobApplications;

}
