package org.ichat.backend.services.account;


import io.jsonwebtoken.Claims;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;

public interface IJwtService {
    /**
     * Set expiration time for token (3 days by default)
     * @param expiration time in milliseconds
     */
    void setExpiration(Long expiration);

    /**
     * Generate token for user with expiration time. <br>
     * Token is encrypted with HS256 algorithm
     *
     * @param user user to generate token
     * @return generated token as string
     */
    String generateToken(User user);

    /**
     * Get email from token
     *
     * @param token token to get email from
     * @return email from token
     */
    String getEmailFromToken(String token);

    /**
     * Get claims from token <br>
     *
     * @param token token to get claims from
     * @return claims from token
     * @throws AccountException if token is invalid or expired
     */
    Claims getClaimsFromToken(String token);
}
