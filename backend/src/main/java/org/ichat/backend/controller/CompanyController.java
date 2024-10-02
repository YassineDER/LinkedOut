package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.patchers.CompanyPatchDTO;
import org.ichat.backend.services.ICompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CompanyController to handle company related requests
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final ICompanyService companyService;

    /**
     * Update company by id
     * @param me current authenticated company
     * @RequestBody patch company patch
     */
    @PutMapping("/me")
    public ResponseEntity<Company> update(User me, @RequestBody @Valid CompanyPatchDTO patch) {
        Company updatedUser = companyService.update(me.getUser_id(), patch);
        return ResponseEntity.ok(updatedUser);
    }
}
