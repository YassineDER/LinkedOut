package org.ichat.backend.service.social.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.service.social.IProfileService;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    @Override
    public JobseekerProfile createJobseekerProfile(Jobseeker jobseeker) {
        JobseekerProfile profile = new JobseekerProfile();
        profile.setUser(jobseeker);
        return profile;
    }

    @Override
    public CompanyStaffProfile createSocialProfile(Company company) {
        CompanyStaffProfile profile = new CompanyStaffProfile();
        profile.setUser(company);
        return profile;
    }

    @Override
    public CompanyStaffProfile createSocialProfile(Admin admin) {
        CompanyStaffProfile profile = new CompanyStaffProfile();
        profile.setUser(admin);
        return profile;
    }
}
