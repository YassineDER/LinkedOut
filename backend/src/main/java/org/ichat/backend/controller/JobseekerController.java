package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.patchers.JobseekerPatchDTO;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobseeker")
public class JobseekerController {
    private final IJobseekerService jobseekerService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Jobseeker> one(@PathVariable Long id) {
        Jobseeker user = jobseekerService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Jobseeker> update(@PathVariable Long id, @Valid @RequestBody JobseekerPatchDTO patch) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jobseeker currentUser = jobseekerService.findBy(userDetails.getUsername());
        if (!currentUser.getUser_id().equals(id) &&
                !currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            throw new AccountException("You are authorized to update only your own account");

        Jobseeker updatedUser = jobseekerService.update(id, patch);
        return ResponseEntity.ok(updatedUser);
    }
}
