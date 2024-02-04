package org.ichat.backend.service;

import org.ichat.backend.model.User;

public interface IAccountVerificationService {
    String verifyToken(String token);
    void sendVerificationEmail(User user);
}
