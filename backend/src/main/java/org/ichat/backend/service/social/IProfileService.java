package org.ichat.backend.service.social;

import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.tables.social.SocialProfile;

public interface IProfileService {
    JobseekerProfile createJobseekerProfile(Jobseeker jobseeker);

    SocialProfile createSocialProfile(Company company);

    SocialProfile createSocialProfile(Admin admin);
}
