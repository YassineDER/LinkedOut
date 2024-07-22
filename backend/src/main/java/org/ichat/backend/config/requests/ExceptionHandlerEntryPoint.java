package org.ichat.backend.config.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exeception.GlobalExceptionAdvice;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component("customAuthenticationEntryPoint")
@RequiredArgsConstructor
public class ExceptionHandlerEntryPoint implements AuthenticationEntryPoint {
    private final GlobalExceptionAdvice advice;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exp)
            throws IOException {
        var error = advice.accountErrorHandler(exp);
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        resp.setStatus(error.getStatusCode().value());
        OutputStream res = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res, error.getBody());
        res.flush();
    }
}