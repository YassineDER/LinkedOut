package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Company;
import org.ichat.backend.model.util.patchers.CompanyPatch;
import org.ichat.backend.service.ICompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMPANY')")
    @PutMapping("/id/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody @Valid CompanyPatch user) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company currentUser = companyService.findBy(userDetails.getUsername());
        if (!currentUser.getUser_id().equals(id) &&
                !currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            throw new AccountException("You are authorized to update only your own account");

        Company updatedUser = companyService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
