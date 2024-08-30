package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.Jobseeker;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.patchers.JobseekerPatchDTO;
import org.ichat.backend.services.IJobseekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Jobseeker to handle requests related to Jobseeker entity.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobseeker")
public class JobseekerController {
    private final IJobseekerService jobseekerService;

    /**
     * Get Jobseeker by id.
     * @param id Id of the Jobseeker.
     * @return Jobseeker with the given id.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Jobseeker> one(@PathVariable Long id) {
        Jobseeker user = jobseekerService.findBy(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Update the current Jobseeker.
     * @param me Current Jobseeker.
     * @param patch Patch to be applied to the Jobseeker.
     * @return Updated Jobseeker.
     */
    @PutMapping("/me")
    public ResponseEntity<Jobseeker> update(User me, @Valid @RequestBody JobseekerPatchDTO patch) {
        Jobseeker updatedUser = jobseekerService.update(me.getUser_id(), patch);
        return ResponseEntity.ok(updatedUser);
    }

}
