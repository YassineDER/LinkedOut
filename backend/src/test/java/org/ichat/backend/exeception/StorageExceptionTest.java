package org.ichat.backend.exeception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageExceptionTest {

    @Test
    void storageException_shouldThrowException() {
        assertThrows(StorageException.class, () -> {
            throw new StorageException();
        });
    }

    @Test
    void storageException_shouldHaveCorrectMessage() {
        Exception exception = assertThrows(StorageException.class, () -> {
            throw new StorageException("Test message");
        });

        assertEquals("Test message", exception.getMessage());
    }

    @Test
    void storageException_shouldHaveCorrectCause() {
        Exception cause = new RuntimeException("Cause");
        Exception exception = assertThrows(StorageException.class, () -> {
            throw new StorageException("Test message", cause);
        });

        assertEquals(cause, exception.getCause());
    }
}