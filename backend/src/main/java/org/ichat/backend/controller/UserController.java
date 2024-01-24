package org.ichat.backend.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.ichat.backend.model.User;
import org.ichat.backend.model.assemblers.UserModelAssembler;
import org.ichat.backend.model.util.UserCredentials;
import org.ichat.backend.service.implementation.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User registeredUser = userService.register(userService.cloneUser(user));
        EntityModel<User> entityModel = userModelAssembler.toModel(registeredUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(registeredUser);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        User user = userService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/verify/{token}")
    public ResponseEntity<?> verifyAccount(@PathVariable String token) {
        User verifiedUser = userService.verifyAccount(token);
        return ResponseEntity.ok(verifiedUser);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        EntityModel<User> entityModel = userModelAssembler.toModel(updatedUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials credentials) {
        userService.login(credentials.getEmail(), credentials.getPassword());
        return ResponseEntity.ok("Verification sent to email to activate account");
    }

}
