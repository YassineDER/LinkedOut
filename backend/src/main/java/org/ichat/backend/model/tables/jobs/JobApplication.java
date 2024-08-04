package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.job.ApplicationStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long job_application_id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIncludeProperties({"user_id", "first_name", "last_name", "email", "image_url", "phone", "cv_url"})
    private Jobseeker jobseeker;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    @JsonIncludeProperties({"job_id", "title", "type", "employer"})
    private Job job;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ApplicationStatus status;

    @Column
    String coverLetter_url;

}
