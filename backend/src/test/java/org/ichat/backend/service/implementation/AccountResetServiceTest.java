package org.ichat.backend.service.implementation;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.AccountReset;
import org.ichat.backend.model.User;
import org.ichat.backend.repository.AccountResetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class AccountResetServiceTest {
    @Mock
    private AccountResetRepository accountResetRepository;
    @Mock
    private AccountReset accountReset;

    @InjectMocks
    private AccountResetService accountResetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void resetPassword_Success() {
        // Given
        String token = "token";
        String encodedPassword = "encodedPassword";
        OffsetDateTime now = OffsetDateTime.now();
        when(accountReset.getUser()).thenReturn(new User());
        when(accountReset.getExpiresAt()).thenReturn(now.plusDays(1));
        when(accountResetRepository.findByToken(token)).thenReturn(Optional.of(accountReset));

        // When
        String result = accountResetService.resetPassword(token, encodedPassword);

        // Then
        verify(accountReset).resetUserPassword(encodedPassword);
        verify(accountResetRepository).save(accountReset);
        assertEquals("Password has been reset successfully, you can login with your new password", result);
    }

    @Test
    void resetPassword_ExpiredToken() {
        // Given
        String token = "token";
        String encodedPassword = "encodedPassword";
        OffsetDateTime now = OffsetDateTime.now();
        when(accountReset.getUser()).thenReturn(new User());
        when(accountReset.getExpiresAt()).thenReturn(now.minusDays(1));
        when(accountResetRepository.findByToken(token)).thenReturn(Optional.of(accountReset));

        // When
        AccountException exception = assertThrows(AccountException.class,
                () -> accountResetService.resetPassword(token, encodedPassword));

        // Then
        assertEquals("The provided token has expired, please retry to send another request.", exception.getMessage());
        verify(accountResetRepository, never()).save(any());
    }



}