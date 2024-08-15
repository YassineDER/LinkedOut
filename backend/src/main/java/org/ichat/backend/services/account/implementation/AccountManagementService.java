package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.AccountCredentialsDTO;
import org.ichat.backend.services.account.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManagementService implements IAccountManagementService {
    private final IAccountResetService accountResetService;
    private final IUserService userService;
    private final IAccountVerificationService accountVerificationService;
    private final PasswordEncoder passwordEncoder;

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
    public String requestPasswordReset(AccountCredentialsDTO credentials) {
        User user = userService.findBy(credentials.getEmail());
        String resetToken = accountResetService.sendResetEmail(user.getEmail());
        accountResetService.saveReset(user, resetToken);

        return "Password reset request has been sent to your email address. Please check your email.";
    }

    @Override
    public boolean userUsingMFA(String email) {
        return userService.findBy(email).getUsing_mfa();
    }
}
