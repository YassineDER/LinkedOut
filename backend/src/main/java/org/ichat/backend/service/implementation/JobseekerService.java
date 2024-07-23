package org.ichat.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.Skill;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;
import org.ichat.backend.repository.JobseekerRepo;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class JobseekerService implements IJobseekerService {
    private final JobseekerRepo jobseekerRepo;

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
    public Jobseeker findBy(Long jobseekerId) {
        return jobseekerRepo.findById(jobseekerId)
                .orElseThrow(() -> new AccountException("Jobseeker not found by id"));
    }

    @Override
    public Jobseeker update(Jobseeker jobseekerToUpdate, JobseekerPatchDTO newJobseeker) throws AccountException {

        if (newJobseeker.getFirst_name() != null)
            jobseekerToUpdate.setFirst_name(newJobseeker.getFirst_name());
        if (newJobseeker.getLast_name() != null)
            jobseekerToUpdate.setLast_name(newJobseeker.getLast_name());
        if (newJobseeker.getAddress() != null)
            jobseekerToUpdate.setAddress(newJobseeker.getAddress());
        if (newJobseeker.getPhone() != null)
            jobseekerToUpdate.setPhone(newJobseeker.getPhone());
        if (newJobseeker.getCv_url() != null)
            jobseekerToUpdate.setCv_url(newJobseeker.getCv_url());
        if (newJobseeker.getImage_url() != null)
            jobseekerToUpdate.setImage_url(newJobseeker.getImage_url());

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

    @Override
    public Set<Skill> addAquiredSkills(Long jobseeker_id, Set<Skill> skills) {
        Jobseeker jobseeker = findBy(jobseeker_id);
        jobseeker.setSkills(skills);
        jobseekerRepo.save(jobseeker);
        return jobseeker.getSkills();
    }

    @Override
    public List<Jobseeker> findSuggested() {
        int total = (int) jobseekerRepo.count();
        var lst = new java.util.ArrayList<>(jobseekerRepo.findAll(PageRequest.of(0, total))
                .getContent().stream().limit(5).toList());
        Collections.shuffle(lst);
        return lst.stream().toList();
    }
}

