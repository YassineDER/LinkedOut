package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.jwt.IJwtService;
import org.ichat.backend.model.Roles;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;
import org.ichat.backend.service.IAccountVerificationService;
import org.ichat.backend.service.IAuthService;
import org.ichat.backend.service.IRoleService;
import org.ichat.backend.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserService userService;
    private final IRoleService roleService;
    private final IAccountVerificationService accountVerificationService;
    private final IJwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse authenticate(String email, String password) {
        User user = userService.findBy(email);
        if (!user.isEnabled())
            throw new AccountException("Account is not verified / disabled");

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        var token = jwtService.generateToken(user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new AuthResponse(token);
    }

    @Override
    public String register(User user) throws AccountException {
        Roles USER_Roles = roleService.getRoleByName("USER");
        user.setUser_roles(Set.of(USER_Roles));
        User insertedUser = userService.add(user);

        accountVerificationService.sendVerificationEmail(insertedUser);
        return "User registered successfully. Please verify your email.";
    }

    @Override
    public String verifyAccount(String token) throws AccountException {
        return accountVerificationService.verifyToken(token);
    }

    @Override
    public User cloneUser(User userToVerify) throws AccountException {
        User user = new User();
        user.setFirst_name(userToVerify.getFirst_name());
        user.setLast_name(userToVerify.getLast_name());
        user.setEmail(userToVerify.getEmail());
        user.setUsername(userToVerify.getUsername());
        user.setPassword(passwordEncoder.encode(userToVerify.getPassword()));
        user.setAddress(userToVerify.getAddress());
        user.setPhone(null);
        user.setImage_url(userToVerify.getImage_url());
        user.setEnabled(false);
        user.setUsing_mfa(false);
        user.setCreatedDate(LocalDateTime.now());

        return user;
    }
}
