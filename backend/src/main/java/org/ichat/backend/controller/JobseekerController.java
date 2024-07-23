package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;
import org.ichat.backend.service.IJobseekerService;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/me")
    public ResponseEntity<Jobseeker> update(User me, @Valid @RequestBody JobseekerPatchDTO patch) {
        Jobseeker updatedUser = jobseekerService.update((Jobseeker) me, patch);
        return ResponseEntity.ok(updatedUser);
    }
}
