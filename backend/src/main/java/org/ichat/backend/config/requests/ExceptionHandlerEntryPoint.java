package org.ichat.backend.config.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.GlobalExceptionAdvice;
import org.ichat.backend.model.util.ErrorDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<ErrorDTO> error = advice.accountErrorHandler(exp);
        if (error == null)
            error = advice.handleGlobalErrors(exp);

        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (error.getBody() == null)
            resp.setStatus(error.getStatusCodeValue());
        else resp.setStatus(error.getBody().getStatus());
        OutputStream res = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res, error.getBody());
        res.flush();
    }
}