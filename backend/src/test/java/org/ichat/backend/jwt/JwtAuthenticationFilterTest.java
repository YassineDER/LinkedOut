package org.ichat.backend.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.User;
import org.ichat.backend.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {
    @Mock
    private IJwtService jwtService;
    @Mock
    private IUserService userService;
    @Mock
    private FilterChain filterChain;
    @InjectMocks
    private JwtAuthenticationFilter filter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testDoFilterInternalWithNoAuthorizationHeader() throws ServletException, IOException {
        filter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternalWithInvalidAuthorizationHeader() throws ServletException, IOException {
        request.addHeader("Authorization", "InvalidToken");
        filter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternalWithValidAuthorizationHeader() throws ServletException, IOException, AccountException {
        request.addHeader("Authorization", "Bearer validToken");
        String email = "test@example.com";
        when(jwtService.getEmailFromToken("validToken")).thenReturn(email);
        User user = mock(User.class);
        when(user.getAuthorities()).thenReturn(Collections.emptyList());
        when(user.getEmail()).thenReturn(email);
        when(user.getUsername()).thenReturn("test");
        when(user.getPassword()).thenReturn("password");
        when(userService.findBy(email)).thenReturn(user);

        filter.doFilterInternal(request, response, filterChain);
        UsernamePasswordAuthenticationToken expectedAuthToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        UsernamePasswordAuthenticationToken actualAuthToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        assertEquals(expectedAuthToken.getPrincipal(), actualAuthToken.getPrincipal());
        assertEquals(expectedAuthToken.getCredentials(), actualAuthToken.getCredentials());
        assertEquals(expectedAuthToken.getAuthorities(), actualAuthToken.getAuthorities());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternalWithUserNotFound() throws ServletException, IOException, AccountException {
        request.addHeader("Authorization", "Bearer validToken");
        String email = "test@example.com";
        when(jwtService.getEmailFromToken("validToken")).thenReturn(email);
        when(userService.findBy(email)).thenThrow(new AccountException("User not found"));

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("User not found from provided authorization token", response.getHeader("X-Error"));

        verify(filterChain).doFilter(request, response);
    }


}