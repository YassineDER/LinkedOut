package org.ichat.backend.service.implementation;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.jwt.IJwtService;
import org.ichat.backend.model.Roles;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;
import org.ichat.backend.model.util.AccountCredentials;
import org.ichat.backend.service.*;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional(dontRollbackOn = AccountExpiredException.class)
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserService userService;
    private final IRoleService roleService;
    private final IAccountVerificationService accountVerificationService;
    private final IAccountResetService accountResetService;
    private final ITwoFactorAuthService twoFactorAuthService;
    private final IJwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse authenticate(AccountCredentials credentials) throws QrGenerationException {
        User user = userService.findBy(credentials.getEmail());
        if (!user.isEnabled()) {
            var new_verif = accountVerificationService.sendVerificationEmail(user.getEmail());
            accountVerificationService.saveVerification(user, new_verif);
            throw new AccountExpiredException("Account is disabled because it's not verified. " +
                    "A new verification email has been sent to your email address, please verify your account.");
        }

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new AccountException("Invalid email or password");

        if (user.getUsing_mfa()) {
            String qrCode = twoFactorAuthService.generateMfaImage(user.getMfa_secret(), user.getEmail());
            return new AuthResponse("User must login using OTP", true, qrCode);
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()));
        var token = jwtService.generateToken(user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new AuthResponse(token);
    }

    @Override
    public String register(User user) throws AccountException {
        Roles USER_Roles = roleService.getRoleByName("USER");
        user.setUser_roles(Set.of(USER_Roles));
        User insertedUser = userService.add(user);

        String verifToken = accountVerificationService.sendVerificationEmail(insertedUser.getEmail());
        accountVerificationService.saveVerification(insertedUser, verifToken);

        return "User registered successfully. Please verify your email.";
    }

    @Override
    public String verifyAccount(String token) throws AccountException {
        return accountVerificationService.verifyToken(token);
    }

    @Override
    public String resetPassword(String token, String newPassword) {
        var encoded = passwordEncoder.encode(newPassword);
        return accountResetService.resetPassword(token, encoded);
    }

    @Override
    public String requestPasswordReset(String email) {
        User user = userService.findBy(email);
        String resetToken = accountResetService.sendResetEmail(user.getEmail());
        accountResetService.saveReset(user, resetToken);

        return "Password reset email sent to your email address. Please check your email.";
    }

    @Override
    public User cloneUser(User userToVerify) {
        User user = new User();
        user.setFirst_name(userToVerify.getFirst_name());
        user.setLast_name(userToVerify.getLast_name());
        user.setEmail(userToVerify.getEmail());
        user.setUsername(userToVerify.getUsername());
        user.setPassword(passwordEncoder.encode(userToVerify.getPassword()));
        user.setAddress(userToVerify.getAddress());
        user.setPhone(null);
        user.setImage_url(userToVerify.getImage_url());
        user.setEnabled(false);
        user.setUsing_mfa(false);
        user.setMfa_secret(null);
        user.setCreatedDate(LocalDateTime.now());

        return user;
    }

    @Override
    public String verifyMFA(AccountCredentials credentials) {
        User user = userService.findBy(credentials.getEmail());
        if (twoFactorAuthService.codeIsValid(user.getMfa_secret(), credentials.getCode())){
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(), credentials.getPassword()));
            var token = jwtService.generateToken(user);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return token;
        }
        else throw new BadCredentialsException("Invalid code");
    }
}
