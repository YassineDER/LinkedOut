package org.ichat.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ichat.backend.model.User;
import org.ichat.backend.model.assemblers.UserModelAssembler;
import org.ichat.backend.repository.UserRepo;
import org.ichat.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;
    private final IUserService userService;
    private final UserModelAssembler userModelAssembler;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

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
