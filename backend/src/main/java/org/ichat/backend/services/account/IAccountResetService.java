package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;

public interface IAccountResetService {
    /**
     * Generate a random code and calls the mail service to send the reset email
     * @param email the email of the user
     * @return the response message as plain text
     * @see org.ichat.backend.services.shared.IMailService
     */
    String sendPasswordResetEmail(String email);

    /**
     * Checks if the token is valid and resets the password.
     * @param token the token that was sent to the user
     * @param encodedPassword the new password encoded
     * @return the response message as plain text
     */
    String resetPassword(String token, String encodedPassword);

    /**
     * Save the reset token for the user in the database
     * @param user the user that requested the reset
     * @param resetToken the reset token that was sent to the user
     */
    void saveReset(User user, String resetToken);
}
