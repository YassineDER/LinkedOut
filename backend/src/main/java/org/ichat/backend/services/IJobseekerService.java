package org.ichat.backend.services;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;

import java.util.List;

public interface IJobseekerService{
    /**
     * Find all jobseekers
     * @return List of jobseekers
     */
    List<Jobseeker> findAll();

    /**
     * Updates a jobseeker
     *
     * @param oldJobseekerId
     * @param newJobseeker
     * @return Jobseeker
     * @throws AccountException
     */
    Jobseeker update(Long oldJobseekerId, JobseekerPatchDTO newJobseeker) throws AccountException;

    /**
     * Create a jobseeker
     * @param jobseeker
     * @return Jobseeker
     */
    Jobseeker create(Jobseeker jobseeker);
}
