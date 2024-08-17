package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.auth.AccountCredentialsDTO;
import org.ichat.backend.model.util.auth.AuthResponseDTO;
import org.ichat.backend.services.account.ITwoFactorAuthService;
import org.ichat.backend.services.account.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.ichat.backend.services.IJobseekerService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final ITwoFactorAuthService twoFactorService;
    private final PasswordEncoder passwordEncoder;
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
    public ResponseEntity<Set<User>> suggested(User me) {
        var suggested = userService.findSuggested(me);
        suggested.forEach(userService::compact);
        return ResponseEntity.ok(suggested);
    }

    @PostMapping("/mfa/{action}")
    @Transactional
    public ResponseEntity<AuthResponseDTO> enableMfa(User me, @PathVariable String action, @RequestBody AccountCredentialsDTO confirmation) throws QrGenerationException {
        if (!passwordEncoder.matches(confirmation.getPassword(), me.getPassword()))
            throw new AccountException("Invalid or missing password", HttpStatus.BAD_REQUEST.value());

        if (action.equals("enable")) {
            if (Boolean.TRUE.equals(me.getUsing_mfa()))
                throw new AccountException("MFA is already enabled", HttpStatus.NO_CONTENT.value());

            me.activateMFA(twoFactorService.generateMfaSecret());
            String qrCode = twoFactorService.generateMfaImage(me.getMfa_secret(), me.getEmail());
            userService.update(me.getUser_id(), me);
            return ResponseEntity.ok(new AuthResponseDTO("MFA enabled", true, qrCode));
        }
        else if (action.equals("disable")) {
            if (Boolean.FALSE.equals(me.getUsing_mfa()))
                throw new AccountException("MFA is already disabled", HttpStatus.NO_CONTENT.value());

            me.deactivateMFA();
            userService.update(me.getUser_id(), me);
            return ResponseEntity.ok(new AuthResponseDTO("MFA disabled"));
        }

        throw new AccountException("Invalid action", HttpStatus.BAD_REQUEST.value());
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

        if (!auth.isAuthenticated())
            throw new AccountException("User not authenticated", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/status/v2")
    public ResponseEntity<User> authenticatedUserV2(User me) {
        return ResponseEntity.ok(me);
    }

}
