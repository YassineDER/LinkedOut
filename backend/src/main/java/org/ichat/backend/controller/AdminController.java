package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Admin;
import org.ichat.backend.model.patchers.AdminPatchDTO;
import org.ichat.backend.service.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final IAdminService adminService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Admin> one(@PathVariable Long id) {
        Admin admin = adminService.findBy(id);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @Valid @RequestBody AdminPatchDTO admin) {
        Admin updatedAdmin = adminService.update(id, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }


}
