package org.ichat.backend.service;

import org.ichat.backend.model.User;

public interface IAccountVerificationService {
    User verifyToken(String token);
    void sendVerificationEmail(User user);
}
