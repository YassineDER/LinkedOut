package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.service.IAuthService;
import org.ichat.backend.service.ICompanyService;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<AuthResponse> verifyAccount(@PathVariable String code) {
        String jwt = authService.verifyAccount(code);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody AccountCredentials credentials) throws QrGenerationException {
        AuthResponse authResponse = authService.authenticate(credentials);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/verify/mfa")
    public ResponseEntity<AuthResponse> verifyMFA(@RequestBody AccountCredentials credentials) {
        if (credentials.getCode() == null)
            return ResponseEntity.badRequest().build();
        String resp = authService.verifyMFA(credentials);
        return ResponseEntity.ok(new AuthResponse(resp));
    }

    @PostMapping("/verify/password")
    public ResponseEntity<AuthResponse> resetPassword(@Valid @RequestBody PasswordRequest req) {
        String resp = authService.resetPassword(req.getReceived_code(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(resp));
    }

//    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<?> authenticatedUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var response = Map.of(
                "authenticated", auth.isAuthenticated(),
                "principal", auth.getPrincipal(),
                "authorities", auth.getAuthorities(),
                "name", auth.getName()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResponse> requestReset(@RequestBody AccountCredentials credentials) {
        if (credentials.getEmail() != null) {
            String resp = authService.requestPasswordReset(credentials.getEmail());
            return ResponseEntity.ok(new AuthResponse(resp));
        } else return ResponseEntity.badRequest().build();
    }
}
