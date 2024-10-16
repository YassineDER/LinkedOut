package org.ichat.backend.services.account.implementation;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.AccountCredentialsDTO;
import org.ichat.backend.model.util.auth.AuthResponseDTO;
import org.ichat.backend.services.account.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManagementService implements IAccountManagementService {
    private final IAccountResetService accountResetService;
    private final IUserService userService;
    private final IAccountVerificationService accountVerificationService;
    private final ITwoFactorAuthService twoFactorService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String validateAccount(String token) {
        return accountVerificationService.verifyEmailCode(token);
    }

    @Override
    public String resetPassword(String token, String newPassword) {
        var encoded = passwordEncoder.encode(newPassword);
        return accountResetService.resetPassword(token, encoded);
    }

    @Override
    public String requestPasswordReset(AccountCredentialsDTO credentials) {
        User user = userService.findByEmail(credentials.getEmail());

        // Validates MFA code if the user has 2FA enabled
        if (user.getUsing_mfa()) {
            if (credentials.getCode() == null)
                throw new AccountException("MFA code is required for this user", HttpStatus.FORBIDDEN.value());
            twoFactorService.verifyMFA(user, credentials);
        }
        String resetToken = accountResetService.sendPasswordResetEmail(user.getEmail());
        accountResetService.saveReset(user, resetToken);

        return "Un code a été envoyé à " + user.getEmail() + ". Veuillez vérifier votre boîte de réception";
    }

    @Override
    public String requestMfaOperation(User user) {
        String codeSent = twoFactorService.requestMfa(user);
        accountVerificationService.saveVerificationRequest(user, codeSent);
        return "Un code a été envoyé à " + user.getEmail() + ". Veuillez vérifier votre boîte de réception";
    }

    @Override
    public AuthResponseDTO performMfaAction(User user, String action, String code) throws QrGenerationException {
        accountVerificationService.verifyMfaRequest(user, code);

        if (action.equals("enable")) {
            if (user.getUsing_mfa())
                throw new AccountException("MFA is already enabled", HttpStatus.BAD_REQUEST.value());

            String secret = twoFactorService.generateMfaSecret();
            user.activateMFA(secret);
            String qrCode = twoFactorService.generateMfaImage(user.getMfa_secret(), user.getEmail());
            userService.update(user.getUser_id(), user);
            return new AuthResponseDTO("MFA is enabled", true, qrCode);
        }
        else if (action.equals("disable")) {
            if (!user.getUsing_mfa())
                throw new AccountException("MFA is already disabled", HttpStatus.BAD_REQUEST.value());

            user.deactivateMFA();
            userService.update(user.getUser_id(), user);
            return new AuthResponseDTO("MFA has been disabled");
        }

        throw new AccountException("Invalid action to perform", HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public boolean userUsingMFA(String email) {
        return userService.findByEmail(email).getUsing_mfa();
    }
}
