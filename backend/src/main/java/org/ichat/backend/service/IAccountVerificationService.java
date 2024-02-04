package org.ichat.backend.service;

import org.ichat.backend.model.User;
import org.ichat.backend.model.util.UserCredentials;

public interface IAccountVerificationService {
    String verifyToken(String token);
    String sendVerificationEmail(String email);

    void saveVerification(User user, String token);
}
