package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ichat.backend.model.User;
import org.ichat.backend.model.assemblers.UserModelAssembler;
import org.ichat.backend.repository.UserRepo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("/user")
public class UserController {
    private final UserRepo userRepo;
    private final UserModelAssembler userModelAssembler;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        // TODO:
        // 1. Check if email is already registered

        // 2. Save user to database
        // 3. Add role to user
        // 4. Send email verification and save link to database
        // 5. Return response
        // NOTE: Surround with a transaction, if any error occurs, rollback with error handling


        EntityModel<User> entityModel = userModelAssembler.toModel(userRepo.save(user));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(userRepo.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> one(@PathVariable String email) {
        return ResponseEntity.ok(userRepo.findByEmail(email));
    }

}
