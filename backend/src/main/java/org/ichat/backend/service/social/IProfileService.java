package org.ichat.backend.service.social;

import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.JobseekerProfile;

public interface IProfileService {

    JobseekerProfile createJobseekerProfile(Jobseeker jobseeker);

    CompanyStaffProfile createSocialProfile(Company company);

    CompanyStaffProfile createSocialProfile(Admin admin);
}
