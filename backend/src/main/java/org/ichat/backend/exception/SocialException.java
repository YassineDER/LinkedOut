package org.ichat.backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.StandardException;

@StandardException
@EqualsAndHashCode(callSuper = true)
@Data
public class SocialException extends RuntimeException {
    private int status;

    public SocialException(String message, int status) {
        super(message);
        this.status = status;
    }

    public SocialException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }
}
