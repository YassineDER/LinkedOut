package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.auth.AccountCredentials;
import org.ichat.backend.model.util.auth.AuthResponse;
import org.ichat.backend.service.account.IAuthService;
import org.ichat.backend.service.account.ITwoFactorAuthService;
import org.ichat.backend.service.account.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.ichat.backend.service.IJobseekerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final ITwoFactorAuthService twoFactorService;
    private final PasswordEncoder passwordEncoder;
    private final IAuthService authService;
    private final IJobseekerService jobseekerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/suggested")
    public ResponseEntity<List<Jobseeker>> suggested() {
        var suggested = jobseekerService.findSuggested();
        return ResponseEntity.ok(suggested);
    }
    

    @PostMapping("/mfa/{action}")
    @Transactional
    public ResponseEntity<AuthResponse> enableMfa(@PathVariable String action, @RequestBody AccountCredentials confirmation) throws QrGenerationException {
        User authenticatedUser = authService.getAuthenticatedUser();

        if (!passwordEncoder.matches(confirmation.getPassword(), authenticatedUser.getPassword())
                || confirmation.getPassword() == null)
            throw new AccountException("Invalid or missing password");

        if (action.equals("enable")) {
            if (Boolean.TRUE.equals(authenticatedUser.getUsing_mfa()))
                throw new AccountException("MFA is already enabled");

            authenticatedUser.activateMFA(twoFactorService.generateMfaSecret());
            String qrCode = twoFactorService.generateMfaImage(authenticatedUser.getMfa_secret(), authenticatedUser.getEmail());
            userService.update(authenticatedUser.getUser_id(), authenticatedUser);
            return ResponseEntity.ok(new AuthResponse("MFA enabled", true, qrCode));
        }
        else if (action.equals("disable")) {
            if (Boolean.FALSE.equals(authenticatedUser.getUsing_mfa()))
                throw new AccountException("MFA is already disabled");
            authenticatedUser.deactivateMFA();
            userService.update(authenticatedUser.getUser_id(), authenticatedUser);
            return ResponseEntity.ok(new AuthResponse("MFA disabled"));
        }

        throw new AccountException("Invalid action");
    }

}
