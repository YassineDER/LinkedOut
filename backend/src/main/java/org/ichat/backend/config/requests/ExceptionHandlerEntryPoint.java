package org.ichat.backend.config.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.exception.GlobalExceptionAdvice;
import org.ichat.backend.model.util.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A custom authentication entry point that handles exceptions thrown during authentication.
 * Somehow, the {@link GlobalExceptionAdvice} is not able to handle exceptions thrown during filter chain execution, so this class is created to handle those exceptions.
 */
@Component("customAuthenticationEntryPoint")
@RequiredArgsConstructor
public class ExceptionHandlerEntryPoint implements AuthenticationEntryPoint {
    private final GlobalExceptionAdvice advice;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exp)
            throws IOException {
        ResponseEntity<ErrorDTO> error = advice.handleAuthenticationErrors(exp);
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        OutputStream res = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res, error.getBody());
        res.flush();
    }
}