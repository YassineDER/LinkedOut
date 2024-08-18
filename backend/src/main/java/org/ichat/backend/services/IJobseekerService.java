package org.ichat.backend.services;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.jobs.Skill;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;

import java.util.List;
import java.util.Set;

public interface IJobseekerService{
    /**
     * Find all jobseekers
     * @return List of jobseekers
     */
    List<Jobseeker> findAll();

    /**
     * Find jobseeker by email
     * @param email
     * @return Jobseeker
     */
    Jobseeker findBy(String email);

    /**
     * Find jobseeker by id
     * @param jobseeker_id
     * @return Jobseeker
     */
    Jobseeker findBy(Long jobseeker_id);

    /**
     * Updates a jobseeker
     * @param oldJobseeker
     * @param newJobseeker
     * @return Jobseeker
     * @throws AccountException
     */
    Jobseeker update(Jobseeker oldJobseeker, JobseekerPatchDTO newJobseeker) throws AccountException;

    /**
     * Create a jobseeker
     * @param jobseeker
     * @return Jobseeker
     */
    Jobseeker create(Jobseeker jobseeker);
}
