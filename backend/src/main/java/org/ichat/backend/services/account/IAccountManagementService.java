package org.ichat.backend.services.account;

import org.ichat.backend.model.util.auth.AccountCredentialsDTO;

public interface IAccountManagementService {
    String validateAccount(String token);

    String resetPassword(String token, String newPassword);
    String requestPasswordReset(AccountCredentialsDTO credentials);

    boolean userUsingMFA(String email);
}
