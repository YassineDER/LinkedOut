package org.ichat.backend.service.account;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.ichat.backend.model.util.auth.*;


public interface IAuthService {
    String registerJobseeker(RegisterJobseekerRequestDTO request, String clientIP);
    String registerCompany(RegisterCompanyRequestDTO request);

    AuthResponseDTO authenticate(AccountCredentialsDTO credentials) throws QrGenerationException;

    String registerAdmin(RegisterAdminRequestDTO request);

    void verifyMFA(AccountCredentialsDTO credentials);
}
