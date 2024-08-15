package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.patchers.CompanyPatchDTO;
import org.ichat.backend.services.ICompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final ICompanyService companyService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Company> one(@PathVariable Long id) {
        Company user = companyService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<Company> update(User me, @RequestBody @Valid CompanyPatchDTO patch) {
        Company currentCompany = (Company) me;
        Company updatedUser = companyService.update(currentCompany, patch);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }
}
