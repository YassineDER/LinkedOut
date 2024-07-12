package org.ichat.backend.config.requests;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.service.account.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserEnabledFilter extends OncePerRequestFilter {
    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        // Apply the check only for requests that start with /api/ and not /api/auth/
        if (requestURI.startsWith("/api/") && !requestURI.startsWith("/api/auth/")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User authenticatedUser) {
                User userInDb = userService.findBy(authenticatedUser.getUser_id());

                if (!userInDb.isEnabled()) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "User is not enabled");
                    return;
                }
            }
        }

        filterChain.doFilter(req, res);
    }
}