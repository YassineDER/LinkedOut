package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.util.GeolocationResponseDTO;
import org.ichat.backend.model.util.RoleType;
import org.ichat.backend.model.util.auth.RegisterAdminRequestDTO;
import org.ichat.backend.model.util.auth.RegisterCompanyRequestDTO;
import org.ichat.backend.model.util.auth.RegisterJobseekerRequestDTO;
import org.ichat.backend.repository.RoleRepository;
import org.ichat.backend.services.IAdminService;
import org.ichat.backend.services.ICompanyService;
import org.ichat.backend.services.IJobseekerService;
import org.ichat.backend.services.account.IAccountVerificationService;
import org.ichat.backend.services.account.IRegisterService;
import org.ichat.backend.services.shared.IGeolocationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService implements IRegisterService {
    private final ICompanyService companyService;
    private final IJobseekerService jobseekerService;
    private final IAdminService adminService;
    private final IAccountVerificationService accountVerificationService;

    private final IGeolocationService geoService;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerJobseeker(RegisterJobseekerRequestDTO request, String clientIP) {
        Roles role = roleRepo.findByName(RoleType.JOBSEEKER).orElseThrow();
        GeolocationResponseDTO geo = geoService.getGeolocationFromIP(clientIP);

        Jobseeker jobseeker = new Jobseeker();
        jobseeker.setFirst_name(request.getFirst_name());
        jobseeker.setLast_name(request.getLast_name());
        jobseeker.setEmail(request.getEmail());
        jobseeker.setUsername(request.getUsername());
        jobseeker.setPassword(passwordEncoder.encode(request.getPassword()));
        jobseeker.setAddress(geo.getCity() + ", " + geo.getCountry());
        jobseeker.setRole(role);

        JobseekerProfile profile = new JobseekerProfile();
        jobseeker.setProfile(profile);
        jobseekerService.create(jobseeker);

        String verifToken = accountVerificationService.sendVerificationEmail(jobseeker.getEmail());
        accountVerificationService.saveVerificationRequest(jobseeker, verifToken);
        return "User registered successfully. Please verify your email.";
    }

    @Override
    public String registerCompany(RegisterCompanyRequestDTO request) {
        Roles role = roleRepo.findByName(RoleType.COMPANY).orElseThrow();
        Company company = companyService.getCompanyBySIREN(request.getSiren());
        company.setUsername(request.getUsername());
        company.setEmail(request.getEmail());
        company.setRole(role);
        company.setPassword(passwordEncoder.encode(request.getPassword()));
        CompanyStaffProfile profile = new CompanyStaffProfile();
        company.setProfile(profile);
        companyService.create(company);

        String verifToken = accountVerificationService.sendVerificationEmail(company.getEmail());
        accountVerificationService.saveVerificationRequest(company, verifToken);
        return "Company registered successfully. Please verify your email.";
    }

    @Override
    public String registerAdmin(RegisterAdminRequestDTO request) {
        Roles role = roleRepo.findByName(RoleType.ADMIN).orElseThrow();
        Admin admin = new Admin();

        admin.setFirst_name(request.getFirst_name());
        admin.setLast_name(request.getLast_name());
        admin.setEmail(request.getEmail());
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(role);

        CompanyStaffProfile profile = new CompanyStaffProfile();
        admin.setProfile(profile);
        adminService.create(admin);

        String verifToken = accountVerificationService.sendVerificationEmail(admin.getEmail());
        accountVerificationService.saveVerificationRequest(admin, verifToken);
        return "Admin registered successfully. Please verify your email.";
    }

}
