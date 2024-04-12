package org.ichat.backend.jwt;


import io.jsonwebtoken.Claims;
import org.ichat.backend.model.tables.User;

public interface IJwtService {
    void setExpiration(Long expiration);

    String generateToken(User user);

    String getEmailFromToken(String token);

    Claims getClaimsFromToken(String token);
}
