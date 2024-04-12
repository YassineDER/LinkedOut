package org.ichat.backend.service;

import org.ichat.backend.model.tables.Roles;

public interface IRoleService {
    Roles getRoleByName(String name);
}
