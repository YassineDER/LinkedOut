package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.model.util.AuthResponse;

public interface IAuthService {
    String register(User user);
    AuthResponse authenticate(String email, String password);
    String verifyAccount(String token);

    User cloneUser(User userToVerify) throws AccountException;
}
