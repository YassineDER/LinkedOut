package org.ichat.backend.config.requests;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.service.account.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserEnabledFilterTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    User user;

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserEnabledFilter userEnabledFilter;

    @Mock
    private FilterChain filterChain;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        user = new User();
        user.setUser_id(1L);
    }

    @Test
    @DisplayName("Allows enabled user through filter")
    void allowsEnabledUserThroughFilter() throws ServletException, IOException {
        user.setEnabled(true);
        request.setRequestURI("/api/user/id/1");

        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(user, null));
        SecurityContextHolder.setContext(securityContext);
        when(userService.findBy(1L)).thenReturn(user);

        userEnabledFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Deny disabled user")
    void blocksDisabledUser() throws ServletException, IOException {
        user.setEnabled(false);

        request.setRequestURI("/api/user/id/1");
        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(user, null));
        SecurityContextHolder.setContext(securityContext);
        when(userService.findBy(1L)).thenReturn(user);

        userEnabledFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, never()).doFilter(request, response);
        assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
    }

    @Test
    @DisplayName("Allows request to auth endpoints")
    void allowsRequestToAuthEndpoints() throws ServletException, IOException {
        request.setRequestURI("/api/auth/authenticate");

        userEnabledFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }
}