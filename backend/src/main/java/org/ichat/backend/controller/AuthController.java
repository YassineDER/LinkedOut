package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.*;
import org.ichat.backend.service.account.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;
    private final HttpServletRequest request;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/jobseeker")
    public ResponseEntity<AuthResponseDTO> registerJobseeker(@Valid @RequestBody RegisterJobseekerRequestDTO reqBody) {
        String clientIP = request.getHeader("X-FORWARDED-FOR");
        String resp = authService.registerJobseeker(reqBody, clientIP);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/company")
    public ResponseEntity<AuthResponseDTO> registerCompany(@Valid @RequestBody RegisterCompanyRequestDTO req) {
        String resp = authService.registerCompany(req);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<AuthResponseDTO> validateAccount(@PathVariable String code) {
        String jwt = authService.validateAccount(code);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AccountCredentialsDTO credentials) throws QrGenerationException {
        AuthResponseDTO authResponseDTO = authService.authenticate(credentials);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/reset-password")
    @Transactional
    public ResponseEntity<AuthResponseDTO> requestReset(@RequestBody AccountCredentialsDTO credentials) {
        if (Boolean.TRUE.equals(authService.userUsingMFA(credentials.getEmail()))) {
            if (credentials.getCode() == null)
                throw new AccountException("MFA code is required for this user");
            authService.verifyMFA(credentials);
        }

        String resp = authService.requestPasswordReset(credentials);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    @PostMapping("/verify/password")
    public ResponseEntity<AuthResponseDTO> resetPassword(@Valid @RequestBody PasswordRequestDTO req) {
        if (!req.getPassword().equals(req.getPassword_confirmation()))
            throw new AccountException("Passwords do not match");
        String resp = authService.resetPassword(req.getReceived_code(), req.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(resp));
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

        if (auth == null || !auth.isAuthenticated())
            throw new AccountException("User not authenticated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/v2")
    public ResponseEntity<User> authenticatedUserV2(User me) {
        return ResponseEntity.ok(me);
    }

    @GetMapping("/sleep")
    public ResponseEntity<String> sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Slept for 3 seconds");
    }

}
