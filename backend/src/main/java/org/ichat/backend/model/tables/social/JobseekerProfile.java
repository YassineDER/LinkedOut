package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichat.backend.model.tables.jobs.Education;
import org.ichat.backend.model.tables.jobs.Experience;
import org.ichat.backend.model.tables.jobs.Skill;

import java.util.List;
import java.util.Set;

/**
 * JobseekerProfile class is a subclass of Profile class.
 * It is used to store the professional information of a jobseeker.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("JOBSEEKER")
public class JobseekerProfile extends Profile {

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"jobseekerProfile"})
    List<Experience> experiences;

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"jobseekerProfile"})
    List<Education> studies;

    @OneToMany
    List<Skill> skills;

}
