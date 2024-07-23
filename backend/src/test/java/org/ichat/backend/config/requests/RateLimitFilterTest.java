package org.ichat.backend.config.requests;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RateLimitFilterTest {
    private MockHttpServletRequest request;
    private HttpServletResponse response;
    private RateLimitFilter limitFilter;

    @Mock
    private FilterChain filterChain;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        limitFilter = new RateLimitFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("Allows request under rate limit")
    void allowsRequestUnderRateLimit() throws IOException, ServletException {
        request.setRemoteAddr("192.168.1.1");

        for (int i = 0; i < 10; i++)
            limitFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(10)).doFilter(request, response);
    }

    @Test
    @DisplayName("Blocks request exceeding rate limit")
    void blocksRequestExceedingRateLimit() throws IOException, ServletException {
        request.setRemoteAddr("192.168.1.2");

        for (int i = 0; i < 11; i++) {
            limitFilter.doFilter(request, response, filterChain);
        }

        verify(filterChain, times(10)).doFilter(request, response);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS.value(), response.getStatus());
        assertTrue(response.getOutputStream().toString().length() > 20);
    }

    @Test
    @DisplayName("Rate limit is enforced per IP address")
    void rateLimitIsEnforcedPerIPAddress() throws IOException, ServletException {
        request.setRemoteAddr("192.168.1.3");

        MockHttpServletRequest request2 = new MockHttpServletRequest();
        HttpServletResponse response2 = new MockHttpServletResponse();
        request2.setRemoteAddr("192.168.1.4");

        for (int i = 0; i < 10; i++) {
            limitFilter.doFilter(request, response, filterChain);
            limitFilter.doFilter(request2, response2, filterChain);
        }

        verify(filterChain, times(10)).doFilter(request, response);
        verify(filterChain, times(10)).doFilter(request2, response2);
    }
}