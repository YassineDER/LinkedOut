package org.ichat.backend.exeception;

import io.jsonwebtoken.JwtException;
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
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(AccountException.class)
    ResponseEntity<Object> accountErrorHandler(AccountException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<Object> badCredentialsErrorHandler(BadCredentialsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Bad credentials");
        body.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(JwtException.class)
    ResponseEntity<Object> jwtErrorHandler(JwtException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
