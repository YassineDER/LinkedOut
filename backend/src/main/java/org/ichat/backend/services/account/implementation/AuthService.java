package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.*;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.CompanyStaffProfile;
import org.ichat.backend.model.tables.social.JobseekerProfile;
import org.ichat.backend.model.tables.social.Profile;
import org.ichat.backend.model.util.GeolocationResponseDTO;
import org.ichat.backend.model.util.RoleType;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.repository.RoleRepository;
import org.ichat.backend.services.*;
import org.ichat.backend.services.account.*;
import org.ichat.backend.services.implementation.AdminService;
import org.ichat.backend.services.shared.IGeolocationService;
import org.ichat.backend.services.social.implementation.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional(dontRollbackOn = AccountExpiredException.class)
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final ICompanyService companyService;
    private final IJobseekerService jobseekerService;
    private final RoleRepository roleRepo;
    private final IJwtService jwtService;
    private final IUserService userService;
    private final IAccountVerificationService accountVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final ITwoFactorAuthService twoFactorAuthService;

    private final IGeolocationService geoService;
    private final AuthenticationManager authenticationManager;
    private final ProfileService profileService;
    private final AdminService adminService;

    @Override
    public AuthResponseDTO authenticate(AccountCredentialsDTO credentials) {
        User user = userService.findBy(credentials.getEmail());

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new AccountException("Invalid email or password", HttpStatus.BAD_REQUEST.value());

        if (!user.isEnabled()) {
            var new_verif = accountVerificationService.sendVerificationEmail(user.getEmail());
            accountVerificationService.saveVerification(user, new_verif);
            throw new AccountExpiredException("Account is not verified yet. " +
                    "A new verification email has been sent to your email address, please verify your account.");
        }

        if (Boolean.TRUE.equals(user.getUsing_mfa())){
            if (credentials.getCode() != null) {
                try {
                    verifyMFA(credentials);
                    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    var token = jwtService.generateToken(user);
                    return new AuthResponseDTO(token);
                } catch (AccountException e) {
                    throw new BadCredentialsException("Invalid MFA code");
                }
            } else throw new AccountException("MFA code is required for this user", HttpStatus.FORBIDDEN.value());
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()));
        var token = jwtService.generateToken(user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new AuthResponseDTO(token);
    }

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

        JobseekerProfile profile = profileService.createJobseekerProfile(jobseeker);
        jobseeker.setProfile(profile);
        jobseekerService.create(jobseeker);

        String verifToken = accountVerificationService.sendVerificationEmail(jobseeker.getEmail());
        accountVerificationService.saveVerification(jobseeker, verifToken);
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
        CompanyStaffProfile profile = profileService.createSocialProfile(company);
        company.setProfile(profile);
        companyService.create(company);

        String verifToken = accountVerificationService.sendVerificationEmail(company.getEmail());
        accountVerificationService.saveVerification(company, verifToken);
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

        CompanyStaffProfile profile = profileService.createSocialProfile(admin);
        admin.setProfile(profile);
        adminService.create(admin);

        String verifToken = accountVerificationService.sendVerificationEmail(admin.getEmail());
        accountVerificationService.saveVerification(admin, verifToken);
        return "Admin registered successfully. Please verify your email.";
    }


    @Override
    public void verifyMFA(AccountCredentialsDTO credentials) {
        User user = userService.findBy(credentials.getEmail());
        boolean codeIsValid = twoFactorAuthService.codeIsValid(user.getMfa_secret(), credentials.getCode());
        if (!codeIsValid)
            throw new AccountException("Invalid MFA code", HttpStatus.BAD_REQUEST.value());
    }

}
