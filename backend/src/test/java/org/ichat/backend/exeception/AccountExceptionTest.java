package org.ichat.backend.exeception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountExceptionTest {
    @Test
    void accountException_shouldThrowException() {
        assertThrows(AccountException.class, () -> {
            throw new AccountException();
        });
    }

    @Test
    void accountException_shouldHaveCorrectMessage() {
        Exception exception = assertThrows(AccountException.class, () -> {
            throw new AccountException("Test message");
        });

        assertEquals("Test message", exception.getMessage());
    }

    @Test
    void accountException_shouldHaveCorrectCause() {
        Exception cause = new RuntimeException("Cause");
        Exception exception = assertThrows(AccountException.class, () -> {
            throw new AccountException("Test message", cause);
        });

        assertEquals(cause, exception.getCause());
    }
}