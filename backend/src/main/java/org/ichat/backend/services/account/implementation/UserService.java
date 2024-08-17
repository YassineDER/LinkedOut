package org.ichat.backend.services.account.implementation;

import org.ichat.backend.services.account.IUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepo;

    @Override
    public User findBy(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new AccountException("User not found by id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findBy(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new AccountException("User not found by email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new AccountException("User not found by username", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    @Override
    public void deleteBy(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(Long userId, User newUser) {
        User userToUpdate = findBy(userId);
        userToUpdate.setUsing_mfa(newUser.getUsing_mfa());
        userToUpdate.setMfa_secret(newUser.getMfa_secret());
        userToUpdate.setImage_url(newUser.getImage_url());
        return userRepo.save(userToUpdate);
    }

    @Override
    public Set<User> findSuggested(User emiter) {
        int total = (int) userRepo.count();
        if (total == 0)
            return Set.of();

        var lst = new ArrayList<>(userRepo.findAll(PageRequest.of(0, total))
                .getContent().stream().limit(6)
                .filter(user -> !user.equals(emiter))
                .filter(user -> user.getRole().equals(emiter.getRole()))
                .toList());
        Collections.shuffle(lst);
        return new HashSet<>(lst);
    }

    @Override
    public User compact(User user) {
        user.setPassword(null);
        user.setUsing_mfa(null);
        user.setEnabled(null);
        user.setMfa_secret(null);
        user.setCreatedDate(null);
        user.setProfile(null);
        user.setUserAccountVerifications(null);
        user.setUserAccountResets(null);
        return user;
    }
}