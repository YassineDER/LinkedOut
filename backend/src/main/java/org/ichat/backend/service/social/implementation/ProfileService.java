package org.ichat.backend.service.social.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.tables.social.SocialProfile;
import org.ichat.backend.service.social.IProfileService;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    @Override
    public JobseekerProfile createJobseekerProfile(Jobseeker jobseeker) {
        return null;
    }

    @Override
    public SocialProfile createSocialProfile(Company company) {
        return null;
    }

    @Override
    public SocialProfile createSocialProfile(Admin admin) {
        return null;
    }
}
