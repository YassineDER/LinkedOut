package org.ichat.backend.service;

import org.ichat.backend.model.Role;

public interface IRoleService {
    Role getRoleByName(String name);
}
