package org.ichat.backend.service;

import org.ichat.backend.model.User;

public interface IAccountVerificationService {
    void verifyToken(String token);
    void sendVerificationEmail(User user);
}
