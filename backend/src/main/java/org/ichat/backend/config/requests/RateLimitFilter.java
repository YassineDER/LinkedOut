package org.ichat.backend.config.requests;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component
@Order(1)
@Slf4j
public class RateLimitFilter implements Filter {
    private final long RATE_LIMIT = 10;
    private final Bucket bucket;

    public RateLimitFilter() {
        this.bucket = createNewBucket();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (bucket.tryConsume(1)) {
            chain.doFilter(req, resp);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            log.warn("Rate limit exceeded for IP: {}", request.getRemoteAddr());
        }
    }

    private Bucket createNewBucket() {
        Refill refill = Refill.greedy(RATE_LIMIT, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(RATE_LIMIT, refill);
        return Bucket.builder().addLimit(limit).build();
    }
}