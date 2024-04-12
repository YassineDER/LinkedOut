package org.ichat.backend.service;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.*;

public interface IAuthService {
    String registerJobseeker(RegisterJobseekerRequest request);
    String registerCompany(RegisterCompanyRequest request);

    AuthResponse authenticate(AccountCredentials credentials) throws QrGenerationException;

    void verifyMFA(AccountCredentials credentials);

    String registerAdmin(RegisterAdminRequest request);

    String validateAccount(String token);

    String resetPassword(String token, String newPassword);
    String requestPasswordReset(String email);

    User getAuthenticatedUser();
}
