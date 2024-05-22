package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/register/jobseeker")
    public ResponseEntity<AuthResponse> registerJobseeker(@Valid @RequestBody RegisterJobseekerRequest req) {
        String resp = authService.registerJobseeker(req);
        return ResponseEntity.ok(new AuthResponse(resp));
    }

    @PostMapping("/register/company")
    public ResponseEntity<AuthResponse> registerCompany(@Valid @RequestBody RegisterCompanyRequest req) {
        String resp = authService.registerCompany(req);
        return ResponseEntity.ok(new AuthResponse(resp));
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<AuthResponse> validateAccount(@PathVariable String code) {
        String jwt = authService.validateAccount(code);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AccountCredentials credentials) throws QrGenerationException {
        AuthResponse authResponse = authService.authenticate(credentials);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/reset-password")
    @Transactional
    public ResponseEntity<AuthResponse> requestReset(@RequestBody AccountCredentials credentials) {
        if (credentials.getEmail() != null) {
            if (Boolean.TRUE.equals(authService.getAuthenticatedUser().getUsing_mfa())) {
                if (credentials.getCode() == null)
                    throw new AccountException("MFA code is required for this user");
                authService.verifyMFA(credentials);
            }

            String resp = authService.requestPasswordReset(credentials);
            return ResponseEntity.ok(new AuthResponse(resp));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/verify/password")
    public ResponseEntity<AuthResponse> resetPassword(@Valid @RequestBody PasswordRequest req) {
        String resp = authService.resetPassword(req.getReceived_code(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(resp));
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String,Object>> authenticatedUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var response = Map.of(
                "authenticated", auth.isAuthenticated(),
                "principal", auth.getPrincipal(),
                "authorities", auth.getAuthorities(),
                "name", auth.getName()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify/captcha")
    public ResponseEntity<RecaptchaResponse> verifyCaptcha(@RequestBody String captcha) {
        RecaptchaResponse resp = authService.captchaIsValid(captcha);
        return ResponseEntity.ok(resp);
    }

}
