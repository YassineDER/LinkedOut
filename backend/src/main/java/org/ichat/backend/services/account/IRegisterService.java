package org.ichat.backend.services.account;

import org.ichat.backend.model.util.auth.RegisterAdminRequestDTO;
import org.ichat.backend.model.util.auth.RegisterCompanyRequestDTO;
import org.ichat.backend.model.util.auth.RegisterJobseekerRequestDTO;

public interface IRegisterService {
    /**
     * Register a jobseeker. <br>
     * This method will create a new jobseeker account and triggers the account verification process.
     * @param request The request object containing the jobseeker's information.
     * @param clientIP The IP address of the client to set the jobseeker's address.
     * @return A message indicating the result of the registration (plain/text).
     *
     * @see RegisterJobseekerRequestDTO
     * @see org.ichat.backend.model.tables.Jobseeker
     * @see IAccountVerificationService
     */
    String registerJobseeker(RegisterJobseekerRequestDTO request, String clientIP);

    /**
     * Register a company. <br>
     * This method will create a new company account and triggers the account verification process.
     * @param request  The request object containing the company's information.
     * @return A message indicating the result of the registration (plain/text).
     * @see RegisterCompanyRequestDTO
     * @see org.ichat.backend.model.tables.Company
     * @see IAccountVerificationService
     */
    String registerCompany(RegisterCompanyRequestDTO request);

    /**
     * Register an admin. <br>
     * This method will create a new admin account and triggers the account verification process.
     * @param request The request object containing the admin's information.
     * @return A message indicating the result of the registration (plain/text).
     * @see RegisterAdminRequestDTO
     * @see org.ichat.backend.model.tables.Admin
     * @see IAccountVerificationService
     */
    String registerAdmin(RegisterAdminRequestDTO request);
}
