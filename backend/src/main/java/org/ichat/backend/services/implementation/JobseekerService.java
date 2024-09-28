package org.ichat.backend.services.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;
import org.ichat.backend.repository.account.JobseekerRepository;
import org.ichat.backend.repository.account.UserRepository;
import org.ichat.backend.services.IJobseekerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobseekerService implements IJobseekerService {
    private final JobseekerRepository jobseekerRepo;
    private final UserRepository userRepo;

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
    public Jobseeker update(Long jobseekerToUpdateId, JobseekerPatchDTO newJobseeker) throws AccountException {
        Jobseeker jobseekerToUpdate = findBy(jobseekerToUpdateId);

        if (newJobseeker.getFirst_name() != null)
            jobseekerToUpdate.setFirst_name(newJobseeker.getFirst_name());
        if (newJobseeker.getLast_name() != null)
            jobseekerToUpdate.setLast_name(newJobseeker.getLast_name());
        if (newJobseeker.getAddress() != null)
            jobseekerToUpdate.setAddress(newJobseeker.getAddress());
        if (newJobseeker.getPhone() != null)
            jobseekerToUpdate.setPhone(newJobseeker.getPhone());

        return jobseekerRepo.save(jobseekerToUpdate);
    }

    @Override
    public Jobseeker create(Jobseeker jobseeker) {
        boolean userExists = userRepo.existsByUsername(jobseeker.getUsername()) || userRepo.existsByEmail(jobseeker.getEmail());
        if (userExists)
            throw new AccountException("Jobseeker already exists with given email or username", HttpStatus.CONFLICT.value());

        return jobseekerRepo.save(jobseeker);
    }
}

