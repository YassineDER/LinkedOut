package org.ichat.backend.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.UserRepo;
import org.ichat.backend.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;

    @Override
    public User findBy(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new AccountException("User not found by id"));
    }

    @Override
    public User findBy(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("User not found by email"));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User add(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new AccountException("The provided email is already registered");
        if (userRepo.findByUsername(user.getUsername()).isPresent())
            throw new AccountException("The provided username is already taken");

        return userRepo.save(user);
    }

    @Override
    public void deleteBy(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(Long userID_toUpdate, User newUser) throws AccountException {
        User userToUpdate = userRepo.findById(userID_toUpdate).orElseThrow(() -> new AccountException("User not found"));

        if (newUser.getFirst_name() != null)
            userToUpdate.setFirst_name(newUser.getFirst_name());
        if (newUser.getLast_name() != null)
            userToUpdate.setLast_name(newUser.getLast_name());
        if (newUser.getAddress() != null)
            userToUpdate.setAddress(newUser.getAddress());
        if (newUser.getPhone() != null)
            userToUpdate.setPhone(newUser.getPhone());
        if (newUser.getImage_url() != null)
            userToUpdate.setImage_url(newUser.getImage_url());
        if (newUser.getEnabled() != null)
            userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        if (newUser.getUsing_mfa() != null)
            userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        if (newUser.getMfa_secret() != null)
            userToUpdate.setMfa_secret(newUser.getMfa_secret());

        return userRepo.save(userToUpdate);
    }

}
