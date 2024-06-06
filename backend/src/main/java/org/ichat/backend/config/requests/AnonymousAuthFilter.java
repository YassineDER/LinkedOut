package org.ichat.backend.config.requests;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

public class AnonymousAuthFilter extends AnonymousAuthenticationFilter {
    private final String key;

    public AnonymousAuthFilter(String key) {
        super(key);
        this.key = key;
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest request) {
        Authentication auth = super.createAuthentication(request);
        if (auth instanceof AnonymousAuthenticationToken)
            return new AnonymousAuthenticationToken(key, "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")) {
                @Override
                public boolean isAuthenticated() {
                    return false;
                }
            };

        return auth;
    }


}
