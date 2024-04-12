package org.ichat.backend.service;

import org.ichat.backend.model.tables.User;

import java.util.List;

public interface IUserService {
    void deleteBy(Long id);
    User findBy(Long id);
    User findBy(String email);
    User findByUsername(String username);
    List<User> findAll();

    User update(Long userId, User newUser);
}
