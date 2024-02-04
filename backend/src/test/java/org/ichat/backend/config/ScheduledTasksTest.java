package org.ichat.backend.config;

import org.ichat.backend.repository.AccountResetRepository;
import org.ichat.backend.repository.AccountVerificationRepository;
import org.ichat.backend.repository.TwoFactorAuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.mockito.Mockito.verify;

public class ScheduledTasksTest {

    @Mock
    private AccountVerificationRepository accountVerificationRepository;

    @Mock
    private AccountResetRepository accountResetRepository;

    @Mock
    private TwoFactorAuthenticationRepository twoFactorAuthenticationRepository;

    @InjectMocks
    private ScheduledTasks scheduledTasks;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteExpiredAccountVerificationCallsRepositoryMethod() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        scheduledTasks.deleteExpiredAccountVerification();
        verify(accountVerificationRepository).deleteAllByExpiresAtBefore(threshold);
    }

    @Test
    public void deleteExpiredAccountResetCallsRepositoryMethod() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(24);
        scheduledTasks.deleteExpiredAccountReset();
        verify(accountResetRepository).deleteAllByExpiresAtBefore(threshold);
    }

    @Test
    public void deleteExpiredTwoFactorAuthenticationCallsRepositoryMethod() {
        OffsetDateTime threshold = OffsetDateTime.now().minusHours(12);
        scheduledTasks.deleteExpiredTwoFactorAuthentication();
        verify(twoFactorAuthenticationRepository).deleteAllByExpiresAtBefore(threshold);
    }

}
