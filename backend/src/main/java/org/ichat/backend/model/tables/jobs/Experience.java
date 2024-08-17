package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @OneToOne
    @JoinColumn(name = "company_id")
    @JsonIncludeProperties({"company_name", "image_url"})
    Company company;

    @Column(nullable = false)
    @NotEmpty(message = "Title is required")
    String title;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    JobType job_type;

    @Column(nullable = false)
    @NotEmpty(message = "Start date is required")
    LocalDate start_date;

    LocalDate end_date;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @JsonIgnore
    JobseekerProfile jobseekerProfile;


}