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
     * Find admin by email
     * @param email Email of the admin
     * @return Admin with the given email
     */
    Admin findBy(String email);

    /**
     * Find admin by id
     * @param admin_id ID of the admin
     * @return Admin with the given id
     */
    Admin findBy(Long admin_id);

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
