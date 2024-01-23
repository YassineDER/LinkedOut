package org.ichat.backend.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.model.Roles;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@AllArgsConstructor
@RequestMapping("/user-relations")
public class UserRelationsController {
    private final RoleRepository roleRepository;

    @DeleteMapping("/account-roles/{id}")
    public void deleteById(@PathVariable Integer id) {
        roleRepository.deleteById(id);
    }

    @PutMapping("/account-roles/{id}")
    public Roles updateById(@PathVariable Integer id, @RequestBody Roles role) {
        var roleToUpdate = roleRepository.findById(id).orElseThrow();
        if (role.getName() != null)
            roleToUpdate.setName(role.getName());
        if (role.getPermissions() != null)
            roleToUpdate.setPermissions(role.getPermissions());

        return roleRepository.save(roleToUpdate);
    }


}