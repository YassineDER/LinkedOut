package org.ichat.backend.service;

import org.ichat.backend.model.Roles;

public interface IRoleService {
    Roles getRoleByName(String name);
}
