package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;

public interface IAccountVerificationService {
    String verifyToken(String token);
    String sendVerificationEmail(String email);

    void saveVerification(User user, String token);
}
