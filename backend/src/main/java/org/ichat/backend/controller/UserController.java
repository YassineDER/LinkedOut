package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.service.ITwoFactorAuthService;
import org.ichat.backend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final ITwoFactorAuthService twoFactorService;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enable-mfa")
    public ResponseEntity<?> enableMfa() {
        User authenticated_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user_in_db = userService.findBy(authenticated_user.getUser_id());

        if (authenticated_user.getUsing_mfa())
            throw new AccountException("MFA is already enabled");

        authenticated_user.activateMFA(twoFactorService.generateMfaSecret());
        userService.update(user_in_db.getUser_id(), authenticated_user);
        return ResponseEntity.noContent().build();
    }

}
