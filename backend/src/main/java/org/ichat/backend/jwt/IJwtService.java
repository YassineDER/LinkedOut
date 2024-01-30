package org.ichat.backend.jwt;

import org.ichat.backend.model.User;

import java.util.Date;
import java.util.Map;

public interface IJwtService {
    String extractEmail(String token);

    Date extractExpiration(String token);

    String generateToken(Map<String, Object> extraClaims, User user);

    boolean isTokenValid(String token, User user);
}