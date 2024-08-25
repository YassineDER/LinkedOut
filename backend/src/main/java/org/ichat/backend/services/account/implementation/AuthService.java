package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.services.account.*;
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
    private final IJwtService jwtService;
    private final IUserService userService;
    private final IAccountVerificationService accountVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final ITwoFactorAuthService twoFactorAuthService;

    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(AccountCredentialsDTO credentials) {
        User user = userService.findBy(credentials.getEmail());

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new AccountException("Invalid email or password", HttpStatus.BAD_REQUEST.value());

        if (!user.isEnabled()) {
            var new_verif = accountVerificationService.sendVerificationEmail(user.getEmail());
            accountVerificationService.saveVerificationRequest(user, new_verif);
            throw new AccountExpiredException("Account is not verified yet. " +
                    "A new verification email has been sent to your email address, please verify your account.");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()));

        if (Boolean.TRUE.equals(user.getUsing_mfa())){
            if (credentials.getCode() != null) {
                try {
                    verifyMFA(credentials);
                    auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                } catch (AccountException e) {
                    throw new BadCredentialsException("Invalid MFA code");
                }
            } else throw new AccountException("MFA code is required for this user", HttpStatus.FORBIDDEN.value());
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtService.generateToken(user);
    }

    @Override
    public void verifyMFA(AccountCredentialsDTO credentials) {
        User user = userService.findBy(credentials.getEmail());
        boolean codeIsValid = twoFactorAuthService.codeIsValid(user.getMfa_secret(), credentials.getCode());
        if (!codeIsValid)
            throw new AccountException("Invalid MFA code", HttpStatus.BAD_REQUEST.value());
    }

}
