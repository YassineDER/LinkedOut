package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Admin;
import org.ichat.backend.model.User;
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
    public Admin update(Long oldUserID, Admin newAdmin) throws AccountException {
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
        User userFoundWithEmail, userFoundWithUsername;
        try {
            userFoundWithEmail = userService.findBy(admin.getEmail());
            userFoundWithUsername = userService.findBy(admin.getUsername());
        } catch (AccountException e) {
            return adminRepo.save(admin);
        }

        if (userFoundWithEmail != null)
            throw new AccountException("User already exists with given email");
        else if (userFoundWithUsername != null)
            throw new AccountException("User already exists with given username");
        else throw new AccountException("User already exists with given email and username");
    }
}
