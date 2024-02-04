package org.ichat.backend.jwt;


import org.ichat.backend.model.User;

public interface IJwtService {
    String generateToken(User user);
    boolean isTokenValid(String token);

    boolean isTokenExpired(String token);

    String getEmailFromToken(String token);
}
