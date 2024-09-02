package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.job.JobType;
import org.ichat.backend.model.util.job.Flow;

import java.util.List;
import java.util.Set;

/**
 * Job entity class, represents the job table in the database.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long job_id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    String title;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    String description;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    String location;

    @NotNull(message = "Salary is required")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    JobType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job workflow is required")
    Flow workflow;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "image_name", "company_name"})
    Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobApplication> jobApplications;

}
