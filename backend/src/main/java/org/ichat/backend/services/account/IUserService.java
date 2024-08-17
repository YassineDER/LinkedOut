package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;

import java.util.List;
import java.util.Set;

public interface IUserService {
    void deleteBy(Long id);
    User findBy(Long id);
    User findBy(String email);
    User findByUsername(String username);
    List<User> findAll();

    UserRepository getUserRepo();

    User update(Long userId, User newUser);

    Set<User> findSuggested(User excluded);

    User compact(User user);
}
