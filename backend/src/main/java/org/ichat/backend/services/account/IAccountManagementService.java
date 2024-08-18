package org.ichat.backend.services.account;

import org.ichat.backend.model.util.auth.AccountCredentialsDTO;

public interface IAccountManagementService {
    /**
     * Calls the account verification service to validate the account
     * @param token the token from the email
     * @return the JWT token if successful
     *
     * @see org.ichat.backend.services.account.IAccountVerificationService
     */
    String validateAccount(String token);

    /**
     * Calls the account reset service to reset the password
     * @param token the token from the email
     * @param newPassword the new password
     * @return the response message as plain text
     *
     * @see org.ichat.backend.services.account.IAccountResetService
     */
    String resetPassword(String token, String newPassword);

    /**
     * Checks if the user exists and calls the account reset service to send the reset email
     * @param credentials the credentials of the account
     * @return the response message as plain text
     *
     * @see org.ichat.backend.services.account.IAccountResetService
     */
    String requestPasswordReset(AccountCredentialsDTO credentials);

    /**
     * Check if the user is using MFA
     * @param email the email of the user
     * @return true if the user is using MFA, false otherwise
     */
    boolean userUsingMFA(String email);
}
