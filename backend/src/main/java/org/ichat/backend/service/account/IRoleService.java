package org.ichat.backend.service.account;

import org.ichat.backend.model.tables.indentity.Roles;

public interface IRoleService {
    Roles getRoleByName(String name);
}
