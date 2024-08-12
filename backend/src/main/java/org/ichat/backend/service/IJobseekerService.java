package org.ichat.backend.service;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.jobs.Skill;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;

import java.util.List;
import java.util.Set;

public interface IJobseekerService{
    List<Jobseeker> findAll();
    Jobseeker findBy(String email);
    Jobseeker findBy(Long jobseeker_id);

    Jobseeker update(Jobseeker oldJobseeker, JobseekerPatchDTO newUser) throws AccountException;
    Jobseeker create(Jobseeker jobseeker);

    Set<Skill> addAquiredSkills(Long jobseeker_id, Set<Skill> skills);

    List<Jobseeker> findSuggested();
}
