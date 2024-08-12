package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.jobs.JobApplication;
import org.ichat.backend.model.tables.social.JobseekerProfile;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("J")
public class Jobseeker extends User {
    @Column(nullable = false)
    @NotEmpty(message = "First name is required")
    String first_name;

    @Column(nullable = false)
    @NotEmpty(message = "Last name is required")
    String last_name;

    String address;

    String phone;

    String cv_url;

    String title;

    @OneToMany(mappedBy = "jobseeker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<JobApplication> jobApplications;

}
