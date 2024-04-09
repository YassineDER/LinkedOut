package org.ichat.backend.service;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.util.auth.*;

public interface IAuthService {
    String registerJobseeker(RegisterJobseekerRequest request);
    String registerCompany(RegisterCompanyRequest request);

    AuthResponse authenticate(AccountCredentials credentials) throws QrGenerationException;

    String registerAdmin(RegisterAdminRequest request);

    String verifyAccount(String token);

    String resetPassword(String token, String newPassword);
    String requestPasswordReset(String email);

    String verifyMFA(AccountCredentials credentials);
}
