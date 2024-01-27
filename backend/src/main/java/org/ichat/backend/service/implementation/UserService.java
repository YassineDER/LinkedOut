package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Roles;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.ichat.backend.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;
    private final RoleService roleService;
    private final AccountVerificationService accountVerificationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) throws AccountException {
        // 1. Check if email is already registered
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new AccountException("Email is already registered");
        if (userRepo.findByUsername(user.getUsername()).isPresent())
            throw new AccountException("Username is already taken");

        // Affect role to user
        Roles USER_Roles = roleService.getRoleByName("USER");
        user.setUser_roles(Set.of(USER_Roles));

        // Save user to database
        User insertedUser = userRepo.save(user);

        // Send verification email
        accountVerificationService.sendVerificationEmail(insertedUser);
        return insertedUser;
    }

    @Override
    public User verifyAccount(String token) throws AccountException {
        return accountVerificationService.verifyToken(token);
    }

    @Override
    public User findBy(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new AccountException("User not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepo.findAll();
        users.forEach(user -> Hibernate.initialize(user.getUser_roles()));
        return users;
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

    @Override
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new AccountException("User maching email not found"));
        if (!user.getEnabled())
            accountVerificationService.sendVerificationEmail(user);
        else throw new AccountException("Login not supported yet");
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(Long id, User user) throws AccountException {
        User userToUpdate = userRepo.findById(id).orElseThrow(() -> new AccountException("User not found"));

        if (user.getFirst_name() != null)
            userToUpdate.setFirst_name(user.getFirst_name());
        if (user.getLast_name() != null)
            userToUpdate.setLast_name(user.getLast_name());
        if (user.getEmail() != null)
            userToUpdate.setEmail(user.getEmail());
        if (user.getAddress() != null)
            userToUpdate.setAddress(user.getAddress());
        if (user.getPhone() != null)
            userToUpdate.setPhone(user.getPhone());
        if (user.getImage_url() != null)
            userToUpdate.setImage_url(user.getImage_url());
        if (user.getEnabled() != null)
            userToUpdate.setUsing_mfa(user.getUsing_mfa());

        return userRepo.save(userToUpdate);
    }

}
