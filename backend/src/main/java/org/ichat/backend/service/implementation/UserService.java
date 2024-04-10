package org.ichat.backend.service.implementation;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.ichat.backend.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;

    @Override
    public User findBy(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new AccountException("User not found by id"));
    }

    @Override
    public User findBy(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new AccountException("User not found by email"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new AccountException("User not found by username"));
    }


    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void deleteBy(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(Long userID_toUpdate, User newUser) throws AccountException {
        User userToUpdate = userRepo.findById(userID_toUpdate).orElseThrow(() -> new AccountException("User not found"));
        userToUpdate.setImage_url(newUser.getImage_url());
        userToUpdate.setEnabled(newUser.getEnabled());
        userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        userToUpdate.setMfa_secret(newUser.getMfa_secret());

        return userToUpdate;
    }

}
