package org.ichat.backend.exeception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
public class StorageException extends RuntimeException{ }
