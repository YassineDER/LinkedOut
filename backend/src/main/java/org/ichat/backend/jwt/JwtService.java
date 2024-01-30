package org.ichat.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtService implements IJwtService {
    private static final String SECRET = System.getenv("JWT_SECRET");

    // Extract username from token
    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // Extract expiration date from token
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generate token with extra claims and user
    @Override
    public String generateToken(Map<String, Object> extraClaims, User user) {
        /// 3 days for prod, 30 days for dev
        int one_hour = 1000 * 60 * 60;
        int expire_at = one_hour * 24 * 3;

        long nowMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expire_at))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token
    @Override
    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSecretKey() {
        byte[] secretBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
