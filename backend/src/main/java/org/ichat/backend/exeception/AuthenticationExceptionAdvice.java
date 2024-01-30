package org.ichat.backend.exeception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(AccountException.class)
    String accountErrorHandler(AccountException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<Object> badCredentialsErrorHandler(BadCredentialsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Bad credentials");
        body.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
