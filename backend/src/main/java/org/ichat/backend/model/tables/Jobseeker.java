package org.ichat.backend.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "jobseeker")
    @JsonIgnore
    private Set<JobApplication> jobApplications;

    @OneToMany(mappedBy = "jobseeker")
    @JsonIgnore
    private Set<Skill> skills;

}
