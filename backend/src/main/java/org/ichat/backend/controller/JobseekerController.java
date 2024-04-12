package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.util.patchers.JobseekerPatch;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobseeker")
public class JobseekerController {
    private final IJobseekerService jobseekerService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Jobseeker user = jobseekerService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'JOBSEEKER')")
    @PutMapping("/id/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody JobseekerPatch patch) {
        Jobseeker updatedUser = jobseekerService.update(id, patch);
        return ResponseEntity.ok(updatedUser);
    }
}
