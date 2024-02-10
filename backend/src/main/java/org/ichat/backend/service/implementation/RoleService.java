package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Roles;
import org.ichat.backend.repository.RoleRepository;
import org.ichat.backend.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public Roles getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new AccountException("Role '" + name + "' not found"));
    }



}
