package org.ichat.backend.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.util.ErrorDTO;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
    * This class is responsible for handling exceptions that occur in the application.
 **/
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {
    private final Environment env;

    /**
        * This method handles exceptions related to accounts.
        * @param ex The exception that occurred.
        * @return The error response as a ResponseEntity.
     **/
    @ResponseBody
    @ExceptionHandler({AccountException.class, AccessDeniedException.class, BadCredentialsException.class, AccountExpiredException.class})
    public ResponseEntity<ErrorDTO> accountErrorHandler(Exception ex) {
        // print stack trace if not in production
        String profile = env.getActiveProfiles()[0];
        if (!profile.equals("prod"))
            ex.printStackTrace();

        // build error response in an appropriate format (ErrorDTO) and return it as bad request
        ErrorDTO error = buildErrorDTO(ex, "Account");
        return ResponseEntity.badRequest().body(error);
    }

    /**
        * This method handles exceptions related to authentication (must not be confused with accounts).
        * @param ex The exception that occurred.
        * @return The error response as a ResponseEntity.
     **/
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleAuthenticationErrors(AuthenticationException ex) {
        // print stack trace if not in production
        String profile = env.getActiveProfiles()[0];
        if (!profile.equals("prod"))
            ex.printStackTrace();

        // build error response in an appropriate format (ErrorDTO) and return it as bad request
        ErrorDTO error = buildErrorDTO(ex, "Authentication");
        return ResponseEntity.badRequest().body(error);
    }

    /**
        * Handles exceptions related to validation errors in the passed body of a request.
     * Precisely, it handles exceptions thrown when a field in the request body does not meet the validation criteria (e.g. @NotEmpty).
        * @param ex The exception that occurred.
        * @return The error response as a ResponseEntity.
     **/
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleObjectNotValidException(MethodArgumentNotValidException ex) {
        Pattern pattern = Pattern.compile("message \\[([^]]+)]");
        Matcher matcher = pattern.matcher(ex.getMessage());
        if (matcher.find()) {
            var res = matcher.results();
            StringBuilder sb = new StringBuilder();
            res.forEach(matchResult -> sb.append(matchResult.group(1)).append(", "));
            String message = sb.toString();
            return ResponseEntity.badRequest().body(message.substring(0, message.length() - 2));
        }

        return ResponseEntity.badRequest().body("Invalid field(s)");
    }

    /**
        * Handles exceptions that are not caught by the other methods.
        * @param ex The exception that occurred.
        * @return The error response as a ResponseEntity.
     **/
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalErrors(Exception ex) {
        // print stack trace if not in production
        String profile = env.getActiveProfiles()[0];
        if (!profile.equals("prod"))
            ex.printStackTrace();

        // build error response in an appropriate format (ErrorDTO) and return it as internal server error
        ErrorDTO error = buildErrorDTO(ex, "Global");
        return ResponseEntity.internalServerError().body(error);
    }

    /**
        * Handles exceptions related to Oracle Cloud storage errors (e.g. uploading, downloading, etc.).
        * @param ex The exception that occurred.
        * @return The error response as a ResponseEntity.
     **/
    @ResponseBody
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageErrors(StorageException ex) {
        // print stack trace if not in production
        String profile = env.getActiveProfiles()[0];
        if (!profile.equals("prod"))
            ex.printStackTrace();

        // build error response in an appropriate format (ErrorDTO) and return it as internal server error
        ErrorDTO error = buildErrorDTO(ex, "Storage");
        return ResponseEntity.internalServerError().body(error);
    }

    /**
        * Builds an error response in an appropriate format (ErrorDTO).
        * @param ex The exception that occurred.
        * @param type The type of the error.
        * @return The error response as an ErrorDTO.
     **/
    private ErrorDTO buildErrorDTO(Exception ex, String type) {
        ErrorDTO error = new ErrorDTO();
        error.setError(ex.getMessage());
        error.setClassName(ex.getClass().getName());
        error.setCause(ex.getCause() == null ? "Unknown" : ex.getCause().getMessage());
        error.setType(type);
        return error;
    }

}
