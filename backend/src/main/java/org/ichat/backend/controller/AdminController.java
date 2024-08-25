package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.patchers.AdminPatchDTO;
import org.ichat.backend.services.IAdminService;
import org.ichat.backend.services.shared.IStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final IAdminService adminService;
    private final IStorageService storageService;

    /**
     * Find an admin by id
     * @param id the id of the admin
     * @return the admin as a response entity
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Admin> one(@PathVariable Long id) {
        Admin admin = adminService.findBy(id);
        return ResponseEntity.ok(admin);
    }

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
