package org.ichat.backend.service.account;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.*;


public interface IAuthService {
    String registerJobseeker(RegisterJobseekerRequestDTO request, String clientIP);
    String registerCompany(RegisterCompanyRequestDTO request);

    AuthResponseDTO authenticate(AccountCredentialsDTO credentials) throws QrGenerationException;

    void verifyMFA(AccountCredentialsDTO credentials);

    String registerAdmin(RegisterAdminRequestDTO request);

    String validateAccount(String token);

    String resetPassword(String token, String newPassword);
    String requestPasswordReset(AccountCredentialsDTO credentials);

    User getAuthenticatedUser();

    boolean userUsingMFA(String email);
}
