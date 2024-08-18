package org.ichat.backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.StandardException;

/**
 * Handle exceptions that occur during account operations.
 * The status code is used to determine the HTTP status code that should be returned to the client.
 */
@EqualsAndHashCode(callSuper = true)
@StandardException
@Data
public class AccountException extends RuntimeException {
    private int status;

    /**
     * Initialize the exception with a message and a status code.
     * @param message The message to be displayed.
     * @param status The status code to be returned.
     */
    public AccountException(String message, int status) {
        super(message);
        this.status = status;
    }

    /**
     * Initialize the exception with a message, a status code, and a cause.
     *
     * @param message The message to be displayed.
     * @param cause   The cause of the exception.
     * @param status  The status code to be returned.
     */
    public AccountException(String message, Exception cause, int status) {
        super(message, cause);
        this.status = status;
    }
}

