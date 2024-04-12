package org.ichat.backend.service.implementation;

import org.ichat.backend.model.util.patchers.AdminPatch;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.repository.AdminRepo;
import org.ichat.backend.service.IAdminService;
import org.ichat.backend.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepo adminRepo;
    private final IUserService userService;

    @Override
    public List<Admin> findAll() {
        return adminRepo.findAll();
    }

    @Override
    public Admin findBy(String email) {
        return adminRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("Admin not found with given email"));
    }

    @Override
    public Admin findBy(Long admin_id) {
        return adminRepo.findById(admin_id)
                .orElseThrow(() -> new AccountException("Admin not found with given id"));
    }

    @Override
    public Admin update(Long oldUserID, AdminPatch newAdmin) throws AccountException {
        Admin adminToUpdate = (Admin) userService.findBy(oldUserID);

        if (adminToUpdate.getFirst_name() != null)
            adminToUpdate.setFirst_name(newAdmin.getFirst_name());
        if (adminToUpdate.getLast_name() != null)
            adminToUpdate.setLast_name(newAdmin.getLast_name());
        if (adminToUpdate.getPhone() != null)
            adminToUpdate.setPhone(newAdmin.getPhone());
        if (adminToUpdate.getTitle() != null)
            adminToUpdate.setTitle(newAdmin.getTitle());

        return adminRepo.save(adminToUpdate);
    }

    @Override
    public Admin add(Admin admin) {
        boolean userExists = adminRepo.findByEmail(admin.getEmail()).isPresent() ||
                adminRepo.findByUsername(admin.getUsername()).isPresent();
        if (userExists)
            throw new AccountException("Admin already exists with given email or username");

        return adminRepo.save(admin);
    }
}
