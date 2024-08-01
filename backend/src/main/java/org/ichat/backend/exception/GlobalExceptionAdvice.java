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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {
    private final Environment env;

    @ResponseBody
    @ExceptionHandler({AccountException.class, AccountExpiredException.class,
            BadCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<ErrorDTO> accountErrorHandler(Exception ex) {
        String profile = env.getActiveProfiles()[0];
        if (profile.equals("dev"))
            ex.printStackTrace();

        ErrorDTO error = buildErrorDTO(ex, "Account");
        return ResponseEntity.status(error.getStatus()).body(error);
    }

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

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalErrors(Exception ex) {
        String profile = env.getActiveProfiles()[0];
        if (profile.equals("dev"))
            ex.printStackTrace();

        ErrorDTO error = buildErrorDTO(ex, "Global");
        return ResponseEntity.internalServerError().body(error);
    }

    @ResponseBody
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageErrors(StorageException ex) {
        String profile = env.getActiveProfiles()[0];
        if (profile.equals("dev"))
            ex.printStackTrace();

        ErrorDTO error = buildErrorDTO(ex, "Storage");
        return ResponseEntity.internalServerError().body(error);
    }


    private ErrorDTO buildErrorDTO(Exception ex, String type) {
        ErrorDTO error = new ErrorDTO();
        error.setError(ex.getMessage());
        error.setStatus(ex.getClass().equals(AccountException.class) ?
                ((AccountException) ex).getStatus() : HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setClassName(ex.getClass().getName());
        error.setCause(ex.getCause() == null ? "Unknown" : ex.getCause().getMessage());
        error.setType(type);
        return error;
    }

}
