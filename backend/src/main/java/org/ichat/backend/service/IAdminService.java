package org.ichat.backend.service;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.patchers.AdminPatchDTO;

import java.util.List;

public interface IAdminService {
    List<Admin> findAll();
    Admin findBy(String email);
    Admin findBy(Long admin_id);

    Admin update(Long oldUserID, AdminPatchDTO newAdmin) throws AccountException;
    Admin add(Admin admin);
}
