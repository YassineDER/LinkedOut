package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;

public interface IAccountVerificationService {

    /**
     * Verify the code sent to the user's email. <br>
     * The verification request is deleted after the user is verified.
     *
     * @param code the code sent to the user's email
     * @return the JWT token of the user
     * @see IJwtService#generateToken(User)
     */
    String verifyEmailCode(String code);

    /**
     * Generate a random code and send it to the user's email.
     *
     * @param email the email of the user
     * @return the code sent to the user's email
     * @see org.ichat.backend.services.shared.IMailService
     */
    String sendVerificationEmail(String email);

    /**
     * Save the verification request of the user in the database.
     *
     * @param user the user to save the verification request
     * @param code the code sent to the user's email
     */
    void saveVerificationRequest(User user, String code);
}
