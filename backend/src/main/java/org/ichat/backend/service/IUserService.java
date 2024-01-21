package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;

public interface IUserService {
    void register(User user) throws AccountException;
}
