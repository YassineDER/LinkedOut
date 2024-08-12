package org.ichat.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.jobs.Skill;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;
import org.ichat.backend.repository.JobseekerRepo;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new AccountException("Jobseeker not found by email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Jobseeker findBy(Long jobseekerId) {
        return jobseekerRepo.findById(jobseekerId)
                .orElseThrow(() -> new AccountException("Jobseeker not found by id", HttpStatus.NOT_FOUND.value()));
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
    public Jobseeker create(Jobseeker jobseeker) {
        boolean userExists = jobseekerRepo.findByEmail(jobseeker.getEmail()).isPresent() ||
                jobseekerRepo.findByUsername(jobseeker.getUsername()).isPresent();
        if (userExists)
            throw new AccountException("Jobseeker already exists with given email or username", HttpStatus.CONFLICT.value());
        return jobseekerRepo.save(jobseeker);
    }

    @Override
    public Set<Skill> addAquiredSkills(Long jobseeker_id, Set<Skill> skills) {
        // TODO: Implement this method
        return null;
    }

    @Override
    public List<Jobseeker> findSuggested() {
        int total = (int) jobseekerRepo.count();
        if (total == 0)
            return List.of();

        var lst = jobseekerRepo.findAll(PageRequest.of(0, total))
                .getContent().stream().limit(5)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(lst);
        return lst.stream().toList();
    }
}

