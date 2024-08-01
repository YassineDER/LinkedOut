package org.ichat.backend.service.implementation;

import org.ichat.backend.model.patchers.AdminPatchDTO;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.repository.AdminRepository;
import org.ichat.backend.service.IAdminService;
import org.ichat.backend.service.account.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final IUserService userService;

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findBy(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new AccountException("Admin not found with given email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Admin findBy(Long admin_id) {
        return adminRepository.findById(admin_id)
                .orElseThrow(() -> new AccountException("Admin not found with given id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Admin update(Long oldUserID, AdminPatchDTO newAdmin) throws AccountException {
        Admin adminToUpdate = (Admin) userService.findBy(oldUserID);

        if (adminToUpdate.getFirst_name() != null)
            adminToUpdate.setFirst_name(newAdmin.getFirst_name());
        if (adminToUpdate.getLast_name() != null)
            adminToUpdate.setLast_name(newAdmin.getLast_name());
        if (adminToUpdate.getPhone() != null)
            adminToUpdate.setPhone(newAdmin.getPhone());
        if (adminToUpdate.getAdmin_title() != null)
            adminToUpdate.setAdmin_title(newAdmin.getTitle());

        return adminRepository.save(adminToUpdate);
    }

    @Override
    public Admin add(Admin admin) {
        boolean userExists = adminRepository.findByEmail(admin.getEmail()).isPresent() ||
                adminRepository.findByUsername(admin.getUsername()).isPresent();
        if (userExists)
            throw new AccountException("Admin already exists with given email or username", HttpStatus.CONFLICT.value());

        return adminRepository.save(admin);
    }
}
