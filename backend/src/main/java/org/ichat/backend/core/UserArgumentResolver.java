package org.ichat.backend.core;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.UserRepository;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves the User object from the current authenticated user. <p>
 * Basically, this class is used to inject the authenticated user object into the controller methods. This is done by adding a User object as a parameter to a controller method.
 */
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserRepository repo;

    // This method is used to check if the parameter type is User
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }

    // This method is used to resolve the User object from the current authenticated user
    @Override
    public Object resolveArgument(MethodParameter param, ModelAndViewContainer container, NativeWebRequest req, WebDataBinderFactory binderFactory) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated())
            throw new AccountException("User not authenticated", HttpStatus.UNAUTHORIZED.value());

        return repo.findByUsername(auth.getName())
                .orElseThrow(() -> new AccountException("User not found", HttpStatus.NOT_FOUND.value()));
    }
}
