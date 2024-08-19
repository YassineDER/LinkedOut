package org.ichat.backend.config.requests;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.services.account.IJwtService;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.services.account.IUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter that checks if the request has a valid JWT token and sets the authentication in the Spring Security context.
 * If the token is invalid, the user will be redirected to the entrypoint. <br> <br>
 * This filter is executed once per request.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final IJwtService jwtService;
    private final IUserService userService;
    private final AuthenticationEntryPoint entryPoint;

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                 FilterChain filterChain) throws ServletException, IOException {
        // Clear the context to prevent leaking user information
        SecurityContextHolder.clearContext();

        // Prevent text/html requests from being processed
        if ("text/html".equalsIgnoreCase(req.getContentType())) {
            res.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "HTML requests are not accepted");
            return;
        }

        try {
            String authorizationHeader = req.getHeader("Authorization");
            // Check if the req has a valid JWT token
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                String email = jwtService.getEmailFromToken(token);
                // Check if its the case, then create the authentication and set it in the context
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userService.findBy(email);
                    var authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (AccountException ex) {
            // If the token is invalid, the user will be redirected to the entrypoint
            entryPoint.commence(req, res, new AuthenticationException(ex.getMessage(), ex) {});
            return;
        }

        // Continue the filter chain in any case.
        filterChain.doFilter(req, res);
    }
}