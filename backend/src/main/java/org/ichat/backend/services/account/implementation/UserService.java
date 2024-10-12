package org.ichat.backend.services.account.implementation;

import org.ichat.backend.services.account.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.account.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepo;

    @Override
    public User findBy(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new AccountException("User not found by id", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new AccountException("User not found by username", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new AccountException("User not found by email", HttpStatus.NOT_FOUND.value()));
    }

    @Override
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
        return userRepo.save(userToUpdate);
    }

    @Override
    public boolean exists(String username, String email) {
        return userRepo.existsByUsername(username) || userRepo.existsByEmail(email);
    }

    @Override
    public Page<User> findSuggested(User emitter, Pageable pageable) {
        int total = (int) userRepo.count();
        if (total == 0)
            return Page.empty();

        Page<User> usersPage = userRepo.findAll(pageable);

        // Filter out the emitter from the results
        List<User> filteredUsers = new ArrayList<>(usersPage.getContent().stream()
                .filter(user -> !user.equals(emitter))
                .toList());
        Collections.shuffle(filteredUsers);
        return new PageImpl<>(filteredUsers, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), total);
    }


    @Override
    public void compact(User user) {
        user.setUsing_mfa(null);
        user.setEnabled(null);
        user.setMfa_secret(null);
        user.setCreatedDate(null);
        user.getProfile().setComments(null);
        user.getProfile().setPosts(null);
    }

    @Override
    public boolean existsByImage(String imagePath) {
        return userRepo.existsByImageName(imagePath);
    }
}