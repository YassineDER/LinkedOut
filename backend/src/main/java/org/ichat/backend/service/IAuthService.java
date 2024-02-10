package org.ichat.backend.service;

import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;

public interface IAuthService {
    String register(User user);
    AuthResponse authenticate(String email, String password);
    String verifyAccount(String token);

    String resetPassword(String token, String newPassword);

    String requestPasswordReset(String email);

    User cloneUser(User userToVerify);
}
