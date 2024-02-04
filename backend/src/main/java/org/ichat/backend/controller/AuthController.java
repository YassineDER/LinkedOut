package org.ichat.backend.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;
import org.ichat.backend.model.util.UserCredentials;
import org.ichat.backend.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Transactional
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        User clonedUser = authService.cloneUser(user);
        return ResponseEntity.ok(authService.register(clonedUser));
    }

    @PostMapping("/verify/{code}")
    public ResponseEntity<AuthResponse> verifyAccount(@PathVariable String code) {
        String jwt = authService.verifyAccount(code);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserCredentials credentials) {
        AuthResponse token = authService.authenticate(credentials.getEmail(), credentials.getPassword());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/status")
    public ResponseEntity<?> commandLineRunner() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var response = Map.of(
                "authenticated", auth.isAuthenticated(),
                "principal", auth.getPrincipal(),
                "authorities", auth.getAuthorities(),
                "name", auth.getName()
        );
        return ResponseEntity.ok(response);
    }
}
