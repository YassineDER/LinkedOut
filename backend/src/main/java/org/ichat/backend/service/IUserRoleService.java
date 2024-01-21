package org.ichat.backend.service;

import org.ichat.backend.model.Role;
import org.ichat.backend.model.User;

public interface IUserRoleService {
    void addRoleToUser(Role role, User user);
}
