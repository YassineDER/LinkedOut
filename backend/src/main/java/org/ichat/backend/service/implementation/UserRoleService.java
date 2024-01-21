package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.model.Role;
import org.ichat.backend.model.User;
import org.ichat.backend.model.UserRole;
import org.ichat.backend.repository.UserRoleRepository;
import org.ichat.backend.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserRoleService implements IUserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public void addRoleToUser(Role role, User user) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);
    }



}
