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
     * Get company by id
     * @param id company id
     * @return company
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Company> one(@PathVariable Long id) {
        Company user = companyService.findBy(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Update company by id
     * @param me current authenticated company
     * @RequestBody patch company patch
     */
    @PutMapping("/me")
    public ResponseEntity<Company> update(User me, @RequestBody @Valid CompanyPatchDTO patch) {
        Company currentCompany = (Company) me;
        Company updatedUser = companyService.update(currentCompany, patch);
        return ResponseEntity.ok(updatedUser);
    }
}
