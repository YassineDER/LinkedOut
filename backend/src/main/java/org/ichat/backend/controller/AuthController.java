package org.ichat.backend.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;
import org.ichat.backend.model.util.UserCredentials;
import org.ichat.backend.service.IAuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@Transactional
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUser(@Valid @RequestBody User user) {
        User clonedUser = authService.cloneUser(user);
        AuthResponse registeredUser = authService.register(clonedUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/verify/{token}")
    public ResponseEntity<?> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserCredentials credentials) {
        AuthResponse user = authService.authenticate(credentials.getEmail(), credentials.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/status")
    public ResponseEntity<?> commandLineRunner() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var response = Map.of(
                "authenticated", auth.isAuthenticated(),
                "principal", auth.getPrincipal(),
                "authorities", auth.getAuthorities()
        );
        return ResponseEntity.ok(response);
    }
}
