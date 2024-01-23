package org.ichat.backend.service;

import org.ichat.backend.model.Roles;
import org.ichat.backend.model.User;

public interface IRoleService {
    Roles getRoleByName(String name);
}
