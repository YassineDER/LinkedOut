package org.ichat.backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.StandardException;

@EqualsAndHashCode(callSuper = true)
@StandardException
@Data
public class AccountException extends RuntimeException {
    private int status;

    public AccountException(String message, int status) {
        super(message);
        this.status = status;
    }

    public AccountException(String message, Exception cause, int value) {
        super(message, cause);
        this.status = value;
    }
}

