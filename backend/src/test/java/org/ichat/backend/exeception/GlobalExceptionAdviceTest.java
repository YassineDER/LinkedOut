package org.ichat.backend.exeception;

import org.ichat.backend.controller.AuthController;
import org.ichat.backend.model.util.auth.AccountCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GlobalExceptionAdviceTest {
    private GlobalExceptionAdvice advice;

    @Mock
    private Environment env;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(env.getActiveProfiles()).thenReturn(new String[]{"test"});
        advice = new GlobalExceptionAdvice(env);
    }

    @Test
    public void testAccountErrorHandler() {
        AccountException exception = new AccountException("Account error");
        ResponseEntity<Object> response = advice.accountErrorHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Account error", ((Map<String, Object>) response.getBody()).get("error"));
    }

    @Test
    public void testHandleObjectNotValidException() throws NoSuchMethodException {
        MethodParameter parameter = new MethodParameter(AuthController.class.getDeclaredMethod("login", AccountCredentials.class), 0);
        BindingResult result = new DirectFieldBindingResult(new Object(), "object");
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, result);
        ResponseEntity<Object> response = advice.handleObjectNotValidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid field(s)", response.getBody());
    }

    @Test
    public void testHandleGlobalErrors() {
        Exception exception = new Exception("Global error");
        ResponseEntity<Object> response = advice.handleGlobalErrors(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Global error", ((Map<String, Object>) response.getBody()).get("error"));
    }

    @Test
    public void testHandleStorageException() {
        StorageException exception = new StorageException("Storage error", new Exception("Storage cause"));
        ResponseEntity<Object> response = advice.handleStorageErrors(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Storage error", ((Map<String, Object>) response.getBody()).get("error"));
        assertEquals("Storage cause", ((Map<String, Object>) response.getBody()).get("cause"));
    }


}