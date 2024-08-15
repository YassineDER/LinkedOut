package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;

public interface IAccountResetService {
    String sendResetEmail(String email);
    String resetPassword(String token, String encodedPassword);

    void saveReset(User user, String resetToken);
}
