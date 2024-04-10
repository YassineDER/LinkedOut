package org.ichat.backend.exeception;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class GlobalExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler({AccountException.class})
    ResponseEntity<Object> accountErrorHandler(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
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
        log.error("A global error occurred", ex);

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", ex.getMessage());
        return ResponseEntity.internalServerError().body(body);
    }

    @ResponseBody
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageErrors(StorageException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", ex.getMessage());
        body.put("cause", ex.getCause().getMessage());
        return ResponseEntity.internalServerError().body(body);
    }


}
