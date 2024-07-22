package org.ichat.backend.config.requests;

import jakarta.servlet.http.HttpServletResponse;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.service.account.IJwtService;
import org.ichat.backend.service.account.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

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
        jwtFilter.doFilterInternal(request, response, (req, res) -> {});
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should not authenticate if Authorization header does not start with Bearer")
    void shouldNotAuthenticateIfAuthorizationHeaderDoesNotStartWithBearer() throws Exception {
        request.addHeader("Authorization", "Basic abcdefg");
        jwtFilter.doFilterInternal(request, response, (req, res) -> {});
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("Should authenticate with valid Bearer token")
    void shouldAuthenticateWithValidBearerToken() throws Exception {
        String token = "validToken";
        String email = "user@example.com";
        User mockUser = mock(User.class);
        when(jwtService.getEmailFromToken(token)).thenReturn(email);
        when(userService.findBy(email)).thenReturn(mockUser);
        request.addHeader("Authorization", "Bearer " + token);

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(mockUser, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    @DisplayName("Should send unauthorized error if token is invalid")
    void shouldSendUnauthorizedErrorIfTokenIsInvalid() throws Exception {
        String token = "invalidToken";
        request.addHeader("Authorization", "Bearer " + token);
        doThrow(new AccountException("Invalid token")).when(jwtService).getEmailFromToken(token);

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

    @Test
    @DisplayName("Should continue filter if token provided and user not found")
    void shouldContinueFilterChainIfUserIsNotFound() throws Exception {
        String token = "validToken";
        String userEmail = "user@example.com";
        request.addHeader("Authorization", "Bearer " + token);
        when(jwtService.getEmailFromToken(token)).thenReturn(userEmail);
        when(userService.findBy(userEmail)).thenThrow(new AccountException("User not found"));

        jwtFilter.doFilterInternal(request, response, (req, res) -> {});

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}