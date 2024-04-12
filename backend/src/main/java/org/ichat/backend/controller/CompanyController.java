package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.patchers.CompanyPatch;
import org.ichat.backend.service.ICompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final ICompanyService companyService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Company user = companyService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMPANY')")
    @PutMapping("/id/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CompanyPatch user) {
        Company updatedUser = companyService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
