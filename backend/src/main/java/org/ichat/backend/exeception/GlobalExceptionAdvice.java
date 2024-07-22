package org.ichat.backend.exeception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {
    private final Environment env;

    private static final String ERROR = "error";
    private static final String STATUS = "status";
    private static final String CAUSE = "cause";
    private static final String TYPE = "type";
    private static final String CLASS = "class";

    @ResponseBody
    @ExceptionHandler({AccountException.class, AccountExpiredException.class,
            BadCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<Object> accountErrorHandler(Exception ex) {
        String profile = env.getActiveProfiles()[0];
        if (profile.equals("dev"))
            ex.printStackTrace();

        Map<String, Object> body = new HashMap<>();
        body.put(TYPE, "Account");
        body.put(CLASS, ex.getClass().getName());
        body.put(ERROR, ex.getMessage());
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(CAUSE, ex.getCause() == null ? "Unknown" : ex.getCause().getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<Object> handleGlobalErrors(Exception ex) {
        String profile = env.getActiveProfiles()[0];
        if (profile.equals("dev"))
            ex.printStackTrace();

        Map<String, Object> body = new HashMap<>();
        body.put(TYPE, "Global");
        body.put(CLASS, ex.getClass().getName());
        body.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(ERROR, ex.getMessage());
        body.put(CAUSE, ex.getCause() == null ? "Unknown" : ex.getCause().getMessage());

        return ResponseEntity.internalServerError().body(body);
    }

    @ResponseBody
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageErrors(StorageException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(TYPE, "Storage");
        body.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(ERROR, ex.getMessage());
        body.put(CAUSE, ex.getCause() == null ? "Unknown" : ex.getCause().getMessage());

        return ResponseEntity.internalServerError().body(body);
    }

}
