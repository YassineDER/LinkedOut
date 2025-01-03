package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.util.job.Flow;

import java.time.LocalDate;

/**
 * Education table, used to store the education of a jobseeker.
 * @see Jobseeker
 * @see JobseekerProfile
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Education {
    @Id
    @GeneratedValue
    Long education_id;

    @NotBlank(message = "School name is required")
    @Column(nullable = false)
    String etablissement;

    @NotBlank(message = "Logo url is required")
    @Column(nullable = false)
    String logo_url = "https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/images/o/default_education_logo.png";

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Education flow is required")
    Flow flow;

    @NotBlank(message = "Degree is required")
    @Column(nullable = false)
    String diplome;

    @Column(length = 500)
    String description;

    @Column(nullable = false)
    @NotNull(message = "Start date is required")
    LocalDate start_date;

    LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @JsonIgnore
    JobseekerProfile jobseekerProfile;
}
