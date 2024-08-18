package org.ichat.backend.services.account;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.util.auth.*;

public interface IAuthService {
    /**
     * Authenticates the user with the provided credentials, after verifying them. <br>
     * If the user has MFA enabled, the user must provide the MFA code as well.
     * If the user is not verified, the account verification service is triggered.
     *
     * @param credentials The credentials of the user.
     * @return The authentication response containing the JWT token and if the user has MFA enabled.
     * @throws QrGenerationException
     *
     * @see AuthResponseDTO
     * @see AccountCredentialsDTO
     * @see IAccountVerificationService
     * @see IUserService
     */
    AuthResponseDTO authenticate(AccountCredentialsDTO credentials) throws QrGenerationException;

    /**
     * Verifies the MFA code provided by the user.
     *
     * @param credentials The credentials of the user.
     */
    void verifyMFA(AccountCredentialsDTO credentials);
}
