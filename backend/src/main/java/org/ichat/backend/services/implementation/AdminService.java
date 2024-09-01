package org.ichat.backend.services.implementation;

import org.ichat.backend.model.patchers.AdminPatchDTO;
import org.ichat.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.repository.AdminRepository;
import org.ichat.backend.services.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

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
        Admin adminToUpdate = findBy(oldUserID);

        if (adminToUpdate.getFirst_name() != null)
            adminToUpdate.setFirst_name(newAdmin.getFirst_name());
        if (adminToUpdate.getLast_name() != null)
            adminToUpdate.setLast_name(newAdmin.getLast_name());
        if (adminToUpdate.getPhone() != null)
            adminToUpdate.setPhone(newAdmin.getPhone());
        if (adminToUpdate.getTitle() != null)
            adminToUpdate.setTitle(newAdmin.getTitle());

        return adminRepository.save(adminToUpdate);
    }

    @Override
    public Admin create(Admin admin) {
        boolean userExists = userRepository.existsByEmail(admin.getEmail()) ||
                userRepository.existsByUsername(admin.getUsername());
        if (userExists)
            throw new AccountException("Admin already exists with given email or username", HttpStatus.CONFLICT.value());

        return adminRepository.save(admin);
    }
}
