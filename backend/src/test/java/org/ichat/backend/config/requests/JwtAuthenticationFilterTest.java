package org.ichat.backend.config.requests;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.services.account.IJwtService;
import org.ichat.backend.services.account.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class JwtAuthenticationFilterTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    private IJwtService jwtService;
    @Mock
    private IUserService userService;

    @InjectMocks
    private JwtAuthenticationFilter jwtFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("Should not authenticate if Authorization header is missing")
    void shouldNotAuthenticateIfAuthorizationHeaderIsMissing() throws Exception {
        jwtFilter.doFilterInternal(request, response, (req, res) -> {
        });
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should not authenticate if Authorization header does not start with Bearer")
    void shouldNotAuthenticateIfAuthorizationHeaderDoesNotStartWithBearer() throws Exception {
        request.addHeader("Authorization", "Basic abcdefg");
        jwtFilter.doFilterInternal(request, response, (req, res) -> {
        });
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should authenticate with valid Bearer token")
    void shouldAuthenticateWithValidBearerToken() throws Exception {
        String token = "validToken";
        String email = "user@example.com";
        User mockUser = mock(User.class);
        when(jwtService.getEmailFromToken(token)).thenReturn(email);
        when(userService.findByEmail(email)).thenReturn(mockUser);
        request.addHeader("Authorization", "Bearer " + token);

        jwtFilter.doFilterInternal(request, response, (req, res) -> {
        });

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(mockUser, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    @DisplayName("Should use entryPoint when exception is thrown in JwtFilter")
    void shouldUseEntryPointWhenExceptionIsThrownInJwtFilter() throws Exception {
        String token = "invalidToken";
        request.addHeader("Authorization", "Bearer " + token);
        AccountException accountException = new AccountException("Invalid token");
        when(jwtService.getEmailFromToken(token)).thenThrow(accountException);

        AuthenticationEntryPoint mockEntryPoint = mock(AuthenticationEntryPoint.class);
        jwtFilter = new JwtAuthenticationFilter(jwtService, userService, mockEntryPoint);
        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        verify(mockEntryPoint).commence(any(HttpServletRequest.class), any(HttpServletResponse.class), any(AuthenticationException.class));
    }

    @Test
    @DisplayName("Should clear SecurityContext before processing request")
    void shouldClearSecurityContextBeforeProcessingRequest() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("user", "password"));
        jwtFilter.doFilterInternal(request, response, (req, res) -> {});
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}