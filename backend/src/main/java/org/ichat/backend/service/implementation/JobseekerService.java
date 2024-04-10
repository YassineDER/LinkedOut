package org.ichat.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Jobseeker;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.JobseekerRepo;
import org.ichat.backend.service.IJobseekerService;
import org.ichat.backend.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobseekerService implements IJobseekerService {
    private final JobseekerRepo jobseekerRepo;
    private final IUserService userService;

    @Override
    public List<Jobseeker> findAll() {
        return jobseekerRepo.findAll();
    }

    @Override
    public Jobseeker findBy(String email) {
        return jobseekerRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("Jobseeker not found by email"));
    }

    @Override
    public Jobseeker findBy(Long jobseeker_id) {
        return jobseekerRepo.findById(jobseeker_id)
                .orElseThrow(() -> new AccountException("Jobseeker not found by id"));
    }

    @Override
    public void deleteBy(Long userID) {
        jobseekerRepo.deleteById(userID);
    }

    @Override
    public Jobseeker update(Long oldUserID, Jobseeker newJobseeker) throws AccountException {
        Jobseeker jobseekerToUpdate = (Jobseeker) userService.update(oldUserID, newJobseeker);

        if (newJobseeker.getFirst_name() != null)
            jobseekerToUpdate.setFirst_name(newJobseeker.getFirst_name());
        if (newJobseeker.getLast_name() != null)
            jobseekerToUpdate.setLast_name(newJobseeker.getLast_name());
        jobseekerToUpdate.setAddress(newJobseeker.getAddress());
        jobseekerToUpdate.setPhone(newJobseeker.getPhone());
        if (newJobseeker.getCv_url() != null)
            jobseekerToUpdate.setCv_url(newJobseeker.getCv_url());
        jobseekerToUpdate.setSkills(newJobseeker.getSkills());

        return jobseekerRepo.save(jobseekerToUpdate);
    }

    @Override
    public Jobseeker add(Jobseeker jobseeker) {
        boolean userExists = jobseekerRepo.findByEmail(jobseeker.getEmail()).isPresent() ||
                jobseekerRepo.findByUsername(jobseeker.getUsername()).isPresent();
        if (userExists)
            throw new AccountException("Jobseeker already exists with given email or username");
        return jobseekerRepo.save(jobseeker);
    }

}

