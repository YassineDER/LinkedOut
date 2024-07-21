package org.ichat.backend.config.requests;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Order(1)
@Slf4j
public class RateLimitFilter implements Filter {
    private final ConcurrentHashMap<String, AtomicLong> requestCount = new ConcurrentHashMap<>();
    private final long RATE_LIMIT = 10;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String ipAddress = req.getRemoteAddr();
        AtomicLong count = requestCount.computeIfAbsent(ipAddress, k -> new AtomicLong());

        if (count.incrementAndGet() > RATE_LIMIT) {
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.getWriter().write("Rate limit exceeded. Please try again later.");
            log.warn("Blocked request from IP: {} due to rate limit exceeded. Counted: {}", ipAddress, count.get());
            return;
        }

        chain.doFilter(req, resp);
    }
}
