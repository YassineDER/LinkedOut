package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;

import java.util.List;

public interface IUserService {
    void deleteBy(Long id);
    User findBy(Long id);
    User findBy(String email);
    User findByUsername(String username);
    List<User> findAll();
    User update(Long userID_toUpdate, User newUser) throws AccountException;
}
