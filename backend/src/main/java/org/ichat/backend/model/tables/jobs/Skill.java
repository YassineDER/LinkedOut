package org.ichat.backend.model.tables.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.social.JobseekerProfile;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long skill_id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Skill name is required")
    String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    @JsonIgnore
    Job job;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "jobseeker_profile_id", referencedColumnName = "jobseeker_profile_id")
    @JsonIgnore
    JobseekerProfile jobseekerProfile;

}
