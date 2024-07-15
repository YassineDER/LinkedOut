package org.ichat.backend.service.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.*;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.indentity.Roles;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.GeolocationResponse;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.service.*;
import org.ichat.backend.service.account.*;
import org.ichat.backend.service.shared.ICaptchaService;
import org.ichat.backend.service.shared.IGeolocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@Transactional(dontRollbackOn = AccountExpiredException.class)
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Value("${admin.secret}")
    private String adminSecret;

    private final IUserService userService;
    private final ICompanyService companyService;
    private final IJobseekerService jobseekerService;
    private final IRoleService roleService;
    private final IAccountVerificationService accountVerificationService;
    private final IAccountResetService accountResetService;
    private final ITwoFactorAuthService twoFactorAuthService;
    private final IJwtService jwtService;

    private final ICaptchaService captchaService;
    private final IGeolocationService geoService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse authenticate(AccountCredentials credentials) {
        if (Boolean.FALSE.equals(credentials.isDebug()))
            captchaIsValid(credentials.getCaptcha());
        User user = userService.findBy(credentials.getEmail());

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new AccountException("Invalid email or password");

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
                    return new AuthResponse(token);
                } catch (AccountException e) {
                    throw new BadCredentialsException("Invalid MFA code");
                }
            } else throw new AccountException("MFA code is required for this user");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()));
        var token = jwtService.generateToken(user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new AuthResponse(token);
    }

    @Override
    public void verifyMFA(AccountCredentials credentials) {
        captchaIsValid(credentials.getCaptcha());
        User user = userService.findBy(credentials.getEmail());
        if (!twoFactorAuthService.codeIsValid(user.getMfa_secret(), credentials.getCode()))
            throw new AccountException("Invalid MFA code");
    }

    @Override
    public String registerJobseeker(RegisterJobseekerRequest request, String clientIP) {
        captchaIsValid(request.getCaptcha());
        Roles role = roleService.getRoleByName("JOBSEEKER");
        GeolocationResponse geo = geoService.getGeolocationFromIP(clientIP);

        Jobseeker jobseeker = new Jobseeker();
        jobseeker.setFirst_name(request.getFirst_name());
        jobseeker.setLast_name(request.getLast_name());
        jobseeker.setEmail(request.getEmail());
        jobseeker.setUsername(request.getUsername());
        jobseeker.setPassword(passwordEncoder.encode(request.getPassword()));
        jobseeker.setAddress(geo.getCity() + ", " + geo.getCountry());
        jobseeker.setUser_roles(Set.of(role));
        jobseekerService.add(jobseeker);

        String verifToken = accountVerificationService.sendVerificationEmail(jobseeker.getEmail());
        accountVerificationService.saveVerification(jobseeker, verifToken);
        return "User registered successfully. Please verify your email.";
    }

    @Override
    public String registerCompany(RegisterCompanyRequest request) {
        captchaIsValid(request.getCaptcha());
        Roles USER_Roles = roleService.getRoleByName("COMPANY");
        Company company = companyService.getCompanyBySIREN(request.getSiren());
        company.setUsername(request.getUsername());
        company.setEmail(request.getEmail());
        company.setPassword(passwordEncoder.encode(request.getPassword()));
        company.setUser_roles(Set.of(USER_Roles));
        companyService.add(company);

        String verifToken = accountVerificationService.sendVerificationEmail(company.getEmail());
        accountVerificationService.saveVerification(company, verifToken);
        return "Company registered successfully. Please verify your email.";
    }

    @Override
    public String registerAdmin(RegisterAdminRequest request) {
        captchaIsValid(request.getCaptcha());
        if (!Objects.equals(request.getAdmin_secret(), adminSecret))
            throw new AccountException("Invalid admin secret");

        Roles USER_Roles = roleService.getRoleByName("ADMIN");
        Admin admin = new Admin();
        admin.setFirst_name(request.getFirst_name());
        admin.setLast_name(request.getLast_name());
        admin.setEmail(request.getEmail());
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setUser_roles(Set.of(USER_Roles));

        String verifToken = accountVerificationService.sendVerificationEmail(admin.getEmail());
        accountVerificationService.saveVerification(admin, verifToken);
        return "Admin registered successfully. Please verify your email.";
    }

    @Override
    public String validateAccount(String token) {
        return accountVerificationService.verifyToken(token);
    }

    @Override
    public String resetPassword(String token, String newPassword) {
        var encoded = passwordEncoder.encode(newPassword);
        return accountResetService.resetPassword(token, encoded);
    }

    @Override
    public String requestPasswordReset(AccountCredentials credentials) {
        captchaIsValid(credentials.getCaptcha());
        User user = userService.findBy(credentials.getEmail());
        String resetToken = accountResetService.sendResetEmail(user.getEmail());
        accountResetService.saveReset(user, resetToken);

        return "Password reset request has been sent to your email address. Please check your email.";
    }

    @Override
    public User getAuthenticatedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null)
            throw new AccountException("User not authenticated");
        return user;
    }

    @Override
    public RecaptchaResponse captchaIsValid(String captcha) {
        RecaptchaResponse response = captchaService.verifyCaptcha(captcha);
        if (!response.isSuccess() || response.getScore() <= 0.5)
            throw new AccountException("Invalid captcha");

        return response;
    }

    @Override
    public boolean userUsingMFA(String email) {
        return userService.findBy(email).getUsing_mfa();
    }
}
