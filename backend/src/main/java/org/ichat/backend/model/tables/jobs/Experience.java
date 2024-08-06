package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.util.job.JobType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Experience {
    @Id
    @GeneratedValue
    Long experience_id;

    @OneToOne(mappedBy = "company", cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id")
    @JsonIncludeProperties({"company_name", "image_url"})
    Company company;

    @Column(nullable = false)
    @NotEmpty(message = "Title is required")
    String title;

    @Column(nullable = false)
    @NotEmpty(message = "Job type is required")
    @Enumerated(EnumType.STRING)
    JobType job_type;

    @Column(nullable = false)
    @NotEmpty(message = "Start date is required")
    LocalDate start_date;

    LocalDate end_date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "jobseeker_profile_id", referencedColumnName = "jobseeker_profile_id")
    @JsonIgnore
    JobseekerProfile jobseekerProfile;


}
