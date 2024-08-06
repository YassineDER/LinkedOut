package org.ichat.backend.model.tables.social;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichat.backend.model.tables.jobs.Education;
import org.ichat.backend.model.tables.jobs.Experience;
import org.ichat.backend.model.tables.jobs.Skill;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobseekerProfile {
    @Id
    @GeneratedValue
    Long jobseeker_profile_id;

    @Column(nullable = false)
    @Length(min = 3, max = 150, message = "Bio must be between 3 and 350 characters")
    String bio = "Bonjour, je suis nouveau sur LinkedOut!";

    @Column(nullable = false)
    String banner_url = "https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/images/o/default_banner.png";

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"jobseekerProfile"})
    Set<Experience> experiences = Set.of();

    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"jobseekerProfile"})
    Set<Education> studies = Set.of();

    // if a profile got deleted, we don't want to delete the skills
    @OneToMany(mappedBy = "jobseekerProfile", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties({"jobseekerProfile"})
    Set<Skill> skills = Set.of();


    @PreRemove
    private void preRemove() {
        skills.forEach(skill -> skill.setJobseekerProfile(null));
    }

}
