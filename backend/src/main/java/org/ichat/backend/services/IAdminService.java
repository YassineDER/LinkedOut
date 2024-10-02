package org.ichat.backend.services;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.patchers.AdminPatchDTO;

import java.util.List;

public interface IAdminService {
    /**
     * Find all admins
     * @return List of all admins
     */
    List<Admin> findAll();

    /**
     * Update an admin
     * @param oldUserID ID of the admin to update
     * @param newAdmin New admin data
     * @return Updated admin
     * @throws AccountException If the admin is not found
     */
    Admin update(Long oldUserID, AdminPatchDTO newAdmin) throws AccountException;

    /**
     * Create a new admin
     * @param admin Admin to create
     * @return Created admin
     */
    Admin create(Admin admin);
}
