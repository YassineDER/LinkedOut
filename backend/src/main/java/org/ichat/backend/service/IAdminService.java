package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.util.patchers.AdminPatch;

import java.util.List;

public interface IAdminService {
    List<Admin> findAll();
    Admin findBy(String email);
    Admin findBy(Long admin_id);

    Admin update(Long oldUserID, AdminPatch newAdmin) throws AccountException;
    Admin add(Admin admin);
}
