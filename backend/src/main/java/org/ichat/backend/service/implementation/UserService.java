package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.ichat.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
//    private final EmailVerificationService emailVerificationService;

    @Override
    public void register(User user) throws AccountException {
        // 1. Check if email is already registered
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new AccountException("Email is already registered");
        }
        // 2. Save user to database
        User insertedUser = userRepo.save(user);
        // 3. Add role to user
        userRoleService.addRoleToUser(roleService.getRoleByName("USER"), insertedUser);
        // 4. Send email verification and save link to database
        // 5. Return response
    }
}
