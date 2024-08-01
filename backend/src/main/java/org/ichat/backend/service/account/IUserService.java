package org.ichat.backend.service.account;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;

import java.util.List;

public interface IUserService {
    void deleteBy(Long id);
    User findBy(Long id);
    User findBy(String email);
    User findByUsername(String username);
    List<User> findAll();

    UserRepository getUserRepository();

    User update(Long userId, User newUser);
}
