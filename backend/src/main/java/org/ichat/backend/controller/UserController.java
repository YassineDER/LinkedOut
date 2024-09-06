package org.ichat.backend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.auth.AccountCredentialsDTO;
import org.ichat.backend.model.util.auth.AuthResponseDTO;
import org.ichat.backend.services.account.IAccountManagementService;
import org.ichat.backend.services.account.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for user related operations
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final IAccountManagementService accountService;
    private final PasswordEncoder passwordEncoder;


    /**
     * @param page Page number to start from
     * @param size Number of users to return
     * @return List of users
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all?page={page}&size={size}")
    public ResponseEntity<List<User>> all(@PathVariable int page, @PathVariable int size) {
        List<User> users = userService.findAll(page, size);
        return ResponseEntity.ok(users);
    }


    /**
     * Deletes a user with the given ID
     * @param id User ID
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Find suggested users for the given user based on role
     * @param me The authenticated user
     * @return Set of suggested users
     */
    @GetMapping("/suggested")
    public ResponseEntity<List<User>> suggested(User me) {
        var suggested = userService.findSuggested(me);
        suggested.forEach(userService::compact);
        return ResponseEntity.ok(suggested);
    }


    @GetMapping("/request-mfa")
    public ResponseEntity<AuthResponseDTO> requestMfa(User me) {
        String resp = accountService.requestMfaOperation(me);
        return ResponseEntity.ok(new AuthResponseDTO(resp));
    }

    /**
     * Enable or disable MFA for the authenticated user
     * @param me The authenticated user
     * @param action Action to perform (enable or disable)
     * @param confirmation Password confirmation in the request body
     * @return Response entity with the result
     * @throws QrGenerationException If there is an error generating the QR code if possible
     */
    @PostMapping("/mfa/{action}")
    @Transactional
    public ResponseEntity<AuthResponseDTO> triggerMFA(User me, @PathVariable String action, @RequestBody AccountCredentialsDTO confirmation)
            throws QrGenerationException {
        if (!passwordEncoder.matches(confirmation.getPassword(), me.getPassword()))
            throw new AccountException("Invalid or missing password", HttpStatus.BAD_REQUEST.value());

        var response = accountService.performMfaAction(me, action, confirmation.getCode());
        return ResponseEntity.ok(response);
    }


    /**
     * Get the status of the authenticated user (Debug purposes recommended). <br>
     * {@return Map} with:
     * <ul>
     *     <li>authenticated: Whether the user is authenticated (true/false)</li>
     *     <li>principal: The (User object) principal of the user</li>
     *     <li>authorities: A list of the authorities (roles) of the user</li>
     *     <li>name: The username of the user</li>
     * </ul>
     * @throws AccountException If the user is not authenticated
     */
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


    /**
     * Get the status of the authenticated user (v2). <br>
     * This method is little similar to {@link UserController#authenticatedUser()} but uses a {@link User} object as a parameter.
     * It's more of a simple way to get the authenticated user as a single object.
     */
    @GetMapping("/status/v2")
    public ResponseEntity<User> authenticatedUserV2(User me) {
        return ResponseEntity.ok(me);
    }

}
