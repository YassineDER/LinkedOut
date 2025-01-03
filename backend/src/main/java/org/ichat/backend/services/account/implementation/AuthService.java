package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.services.account.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @Override
    public AuthResponseDTO authenticate(AccountCredentialsDTO credentials) {
        User user = userService.findByEmail(credentials.getEmail());

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new AccountException("Invalid email or password", HttpStatus.BAD_REQUEST.value());

        if (!user.isEnabled()) {
            var new_verif = accountVerificationService.sendVerificationEmail(user.getEmail());
            accountVerificationService.saveVerificationRequest(user, new_verif);
            throw new AccountExpiredException("Account is not verified yet. " +
                    "A new verification email has been sent to " + user.getEmail());
        }

        if (user.getUsing_mfa()){
            if (credentials.getCode() != null) {
                try {
                    twoFactorAuthService.verifyMFA(user, credentials);
                } catch (AccountException e) {
                    throw new BadCredentialsException("Invalid MFA code");
                }
            } else return new AuthResponseDTO("MFA required", true, null);
        }

        return new AuthResponseDTO(jwtService.generateToken(user));
    }

}
