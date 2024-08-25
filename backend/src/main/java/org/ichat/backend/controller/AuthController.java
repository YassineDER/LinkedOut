package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.services.account.IAccountManagementService;
import org.ichat.backend.services.account.IAuthService;
import org.ichat.backend.services.account.IRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication requests
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;
    private final IRegisterService registerService;
    private final IAccountManagementService accountService;
    private final HttpServletRequest request;

    /**
     * Registers a jobseeker account, it gets also the client public IP address.
     * @param reqBody Request body containing jobseeker details
     * @return Response entity containing the JWT token
     */
    @PostMapping("/register/jobseeker")
    public ResponseEntity<AuthResponseDTO> registerJobseeker(@Valid @RequestBody RegisterJobseekerRequestDTO reqBody) {
        String clientIP = request.getHeader("X-FORWARDED-FOR");
        String resp = registerService.registerJobseeker(reqBody, clientIP);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponseDTO(resp));
    }

    /**
     * Registers a company account
     * @param req Request body containing company details
     * @return Response entity containing the JWT token
     */
    @PostMapping("/register/company")
    public ResponseEntity<AuthResponseDTO> registerCompany(@Valid @RequestBody RegisterCompanyRequestDTO req) {
        String resp = registerService.registerCompany(req);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    /**
     * Validates the account using the code sent to the user's email
     * @param code Code sent to the user's email
     * @return Response entity containing the JWT token
     */
    @GetMapping("/verify/{code}")
    public ResponseEntity<AuthResponseDTO> validateAccount(@PathVariable String code) {
        String jwt = accountService.validateAccount(code);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

    /**
     * Authenticates a user
     * @param credentials Request body containing user credentials
     * @return Response entity containing the JWT token
     * @throws QrGenerationException if there is an error generating the QR code (in case the user has 2FA enabled)
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AccountCredentialsDTO credentials) throws QrGenerationException {
        String token = authService.authenticate(credentials);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    /**
     * Requests a password reset for a user
     * @param credentials Request body containing user credentials
     *                  (email and code if the user has 2FA enabled)
        * @return Response entity containing the response message (success or failure)
     */
    @PostMapping("/reset-password")
    @Transactional
    public ResponseEntity<AuthResponseDTO> requestReset(@RequestBody AccountCredentialsDTO credentials) {
        if (Boolean.TRUE.equals(accountService.userUsingMFA(credentials.getEmail()))) {
            if (credentials.getCode() == null)
                throw new AccountException("MFA code is required for this user", HttpStatus.FORBIDDEN.value());
            authService.verifyMFA(credentials);
        }

        String resp = accountService.requestPasswordReset(credentials);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    /**
     * Verifies the password reset code and sets the new password
     * @param req Request body containing the received code and the new password
     * @return Response entity containing the response message (success or failure)
     */
    @PostMapping("/verify/password")
    public ResponseEntity<AuthResponseDTO> resetPassword(@Valid @RequestBody PasswordRequestDTO req) {
        if (!req.getPassword().equals(req.getPassword_confirmation()))
            throw new AccountException("Passwords do not match", HttpStatus.BAD_REQUEST.value());
        String resp = accountService.resetPassword(req.getReceived_code(), req.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    /**
     * A test endpoint to simulate a slow request
     */
    @GetMapping("/sleep")
    public ResponseEntity<AuthResponseDTO> sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        return ResponseEntity.ok(new AuthResponseDTO("Slept for 3 seconds"));
    }

}
