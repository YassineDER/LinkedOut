package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.job.ApplicationStatus;

/**
 * Represents a job application made by a jobseeker to a job.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long job_application_id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "first_name", "last_name", "email", "image_name", "phone", "resumee"})
    Jobseeker jobseeker;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    @JsonIncludeProperties({"job_id", "title", "type", "employer"})
    Job job;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    ApplicationStatus status;

    String cover_letter;
}
