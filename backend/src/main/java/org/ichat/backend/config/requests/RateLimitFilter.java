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

/**
 * Filter that limits the number of requests per minute. If the limit is exceeded, the client will receive a 429 status code (TOO_MANY_REQUESTS).
 * <br> <br>
 * The limit is set to 10 requests per minute by default.
 * <br> <br>
 * The filter uses the Bucket4j library to implement the rate limiting.
 * @apiNote I don't have any idea if this really works, we will find out once we get a DDOS attack.
 */
@Component
@Order(1)
@Slf4j
public class RateLimitFilter implements Filter {
    private final long RATE_LIMIT = 10; // number of requests per minute (recommended range: 10-100)
    private final Bucket bucket;

    // Initialize the bucket with the rate limit
    public RateLimitFilter() {
        this.bucket = createNewBucket();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // Check if the client has exceeded the rate limit
        if (bucket.tryConsume(1)) {
            chain.doFilter(req, resp);
        } else {
            // If the limit is exceeded, send a 429 status code (TOO_MANY_REQUESTS)
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            log.warn("Rate limit exceeded for IP: {}", request.getRemoteAddr());
        }
    }


    // Create a new bucket with the rate limit and a refill rate of 1 request per minute
    private Bucket createNewBucket() {
        Refill refill = Refill.greedy(RATE_LIMIT, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(RATE_LIMIT, refill);
        return Bucket.builder().addLimit(limit).build();
    }
}