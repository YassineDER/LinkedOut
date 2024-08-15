package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.jobs.Education;
import org.ichat.backend.model.tables.jobs.Experience;
import org.ichat.backend.model.tables.jobs.Skill;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("JOBSEEKER")
public class JobseekerProfile extends Profile {

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    Set<Experience> experiences;

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    Set<Education> studies;

    @OneToMany
    Set<Skill> skills;

}
