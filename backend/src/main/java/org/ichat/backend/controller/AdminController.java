package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.patchers.AdminPatchDTO;
import org.ichat.backend.services.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final IAdminService adminService;

    /**
     * Update an admin by id
     * @param id the id of the admin
     * @param admin the admin to update
     */
    @PutMapping("/id/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @Valid @RequestBody AdminPatchDTO admin) {
        Admin updatedAdmin = adminService.update(id, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

}
