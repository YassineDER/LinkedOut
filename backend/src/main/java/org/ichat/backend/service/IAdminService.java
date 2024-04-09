package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> findAll();
    Admin findBy(String email);
    Admin findBy(Long admin_id);

    Admin update(Long oldUserID, Admin newAdmin) throws AccountException;
    Admin add(Admin admin);
}
