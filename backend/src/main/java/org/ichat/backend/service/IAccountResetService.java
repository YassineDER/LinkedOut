package org.ichat.backend.service;

import org.ichat.backend.model.User;

public interface IAccountResetService {
    String sendResetEmail(String email);
    String resetPassword(String token, String encodedPassword);

    void saveReset(User user, String resetToken);
}
