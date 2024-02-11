package org.ichat.backend.service;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;
import org.ichat.backend.model.util.AccountCredentials;

public interface IAuthService {
    String register(User user);
    AuthResponse authenticate(AccountCredentials credentials) throws QrGenerationException;
    String verifyAccount(String token);

    String resetPassword(String token, String newPassword);
    String requestPasswordReset(String email);

    User cloneUser(User userToVerify);

    String verifyMFA(AccountCredentials credentials);
}
