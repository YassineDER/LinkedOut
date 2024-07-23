package org.ichat.backend.config.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichat.backend.exception.GlobalExceptionAdvice;
import org.ichat.backend.model.util.ErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerEntryPointTests {

    @Mock
    private GlobalExceptionAdvice advice;

    @InjectMocks
    private ExceptionHandlerEntryPoint entryPoint;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AuthenticationException authException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        authException = mock(AuthenticationException.class);
    }

    @Test
    @DisplayName("Should correctly handle authentication exception with custom error")
    void shouldCorrectlyHandleAuthenticationExceptionWithCustomError() throws Exception {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(401);
        errorDTO.setError("Invalid credentials");
        errorDTO.setClassName("org.springframework.security.authentication.BadCredentialsException");
        errorDTO.setCause("Bad credentials");

        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
        when(advice.accountErrorHandler(authException)).thenReturn(responseEntity);

        entryPoint.commence(request, response, authException);

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());

        ObjectMapper mapper = new ObjectMapper();
        ErrorDTO responseBody = mapper.readValue(response.getContentAsByteArray(), ErrorDTO.class);
        assertEquals("Invalid credentials", responseBody.getError());
        assertEquals("Bad credentials", responseBody.getCause());
    }

    @Test
    @DisplayName("Should handle null authentication exception gracefully")
    void shouldHandleNullAuthenticationExceptionGracefully() throws Exception {
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(advice.accountErrorHandler(null)).thenReturn(responseEntity);

        entryPoint.commence(request, response, null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should use global error handler when account error handler returns null")
    void shouldUseGlobalErrorHandlerWhenAccountErrorHandlerReturnsNull() throws Exception {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(500);
        errorDTO.setError("Error");
        errorDTO.setClassName("org.springframework.dao.DataIntegrityViolationException");
        errorDTO.setCause("Data integrity violation");

        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        when(advice.accountErrorHandler(authException)).thenReturn(null);
        when(advice.handleGlobalErrors(authException)).thenReturn(responseEntity);

        entryPoint.commence(request, response, authException);

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

        ObjectMapper mapper = new ObjectMapper();
        ErrorDTO responseBody = mapper.readValue(response.getContentAsByteArray(), ErrorDTO.class);
        assertEquals("Error", responseBody.getError());
        assertEquals("Data integrity violation", responseBody.getCause());
    }
}