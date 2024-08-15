package org.ichat.backend.services.account.implementation;

import org.ichat.backend.services.account.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User findBy(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AccountException("User not found by id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findBy(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AccountException("User not found by email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AccountException("User not found by username", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public void deleteBy(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long userId, User newUser) {
        User userToUpdate = findBy(userId);
        userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        userToUpdate.setMfa_secret(newUser.getMfa_secret());
        userToUpdate.setImage_url(newUser.getImage_url());
        return userRepository.save(userToUpdate);
    }
}