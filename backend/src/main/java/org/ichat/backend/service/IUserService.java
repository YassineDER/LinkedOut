package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;

import java.util.List;

public interface IUserService {
    User register(User user) throws AccountException;
    void delete(Long id);
    User update(Long id, User user) throws AccountException;

    User findBy(Long id);
    List<User> findAll();

    User verifyAccount(String token) throws AccountException;
    User cloneUser(User user);
}
