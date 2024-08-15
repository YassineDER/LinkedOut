package org.ichat.backend.services.implementation;

import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;
import org.ichat.backend.services.account.implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        assertNotNull(userService.findBy(1L));
        assertInstanceOf(User.class, userService.findBy(1L));
    }

    @Test
    void shouldNotFindUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> userService.findBy(1L));
    }

    @Test
    void shouldFindUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        assertNotNull(userService.findBy("test@test.com"));
        assertInstanceOf(User.class, userService.findBy("test@test.com"));
    }

    @Test
    void shouldNotFindUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> userService.findBy("test@test.com"));
    }

    @Test
    void shouldFindUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));
        assertNotNull(userService.findByUsername("username"));
    }

    @Test
    void shouldNotFindUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> userService.findByUsername("username"));
    }

    @Test
    void shouldDeleteUserById() {
        User user = new User();
        user.setUser_id(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteBy(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldUpdateUser() {
        User user = new User();
        user.setImage_url("url");
        user.setEnabled(true);
        user.setUsing_mfa(true);
        user.setMfa_secret("secret");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.update(1L, user);

        assertEquals("url", updatedUser.getImage_url());
        assertTrue(updatedUser.getEnabled());
        assertTrue(updatedUser.getUsing_mfa());
        assertEquals("secret", updatedUser.getMfa_secret());
    }

    @Test
    void shouldNotUpdateUserWhenNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountException.class, () -> userService.update(1L, new User()));
    }
}