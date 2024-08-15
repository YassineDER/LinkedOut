package org.ichat.backend.services.account.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email + "."));
        if (user.getRole() == null)
            throw new AccountException("User has no roles", HttpStatus.UNAUTHORIZED.value());

        return new User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
