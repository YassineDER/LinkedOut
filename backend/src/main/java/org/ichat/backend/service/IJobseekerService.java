package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.Skill;
import org.ichat.backend.model.util.patchers.JobseekerPatchDTO;

import java.util.List;
import java.util.Set;

public interface IJobseekerService{
    List<Jobseeker> findAll();
    Jobseeker findBy(String email);
    Jobseeker findBy(Long jobseeker_id);

    Jobseeker update(Long oldUserID, JobseekerPatchDTO newUser) throws AccountException;
    Jobseeker add(Jobseeker jobseeker);

    Set<Skill> addAquiredSkills(Long jobseeker_id, Set<Skill> skills);

    List<Jobseeker> findSuggested();
}
