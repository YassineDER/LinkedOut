package org.ichat.backend.service;

import org.ichat.backend.model.User;

public interface IAccountVerificationService {
    String verifyToken(String token);
    String sendVerificationEmail(String email);

    void saveVerification(User user, String token);
}
