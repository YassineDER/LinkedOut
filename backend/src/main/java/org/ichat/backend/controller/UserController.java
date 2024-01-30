package org.ichat.backend.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ichat.backend.model.User;
import org.ichat.backend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        User user = userService.findBy(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

}
