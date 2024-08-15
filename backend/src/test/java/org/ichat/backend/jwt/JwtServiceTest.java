package org.ichat.backend.jwt;

import io.jsonwebtoken.Claims;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.services.account.IJwtService;
import org.ichat.backend.services.account.implementation.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {
    @Mock
    private User user;
    private IJwtService jwtService;
    private String token;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
        when(user.getEmail()).thenReturn("test@test.com");
        token = jwtService.generateToken(user);
    }

    @Test
    public void testGetClaimsFromToken() {
        Claims claims = jwtService.getClaimsFromToken(token);
        assertDoesNotThrow(() -> jwtService.getClaimsFromToken(token));
        assertThrows(AccountException.class, () -> jwtService.getClaimsFromToken("invalid,token,test"));
    }

    @Test
    public void testGenerateToken() {
        token = jwtService.generateToken(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    public void testIsTokenExpired() throws InterruptedException {
        jwtService.setExpiration(1L);
        token = jwtService.generateToken(user);
        Thread.sleep(500);
        assertThrows(AccountException.class, () -> jwtService.getClaimsFromToken(token));
    }

    @Test
    public void testGetEmailFromToken() {
        assertEquals(user.getEmail(), jwtService.getEmailFromToken(token));
        assertThrows(AccountException.class, () -> jwtService.getEmailFromToken("invalid,token,test"));
        Claims claims = jwtService.getClaimsFromToken(token);
        Date now = new Date();
        claims.setExpiration(new Date(now.getTime() - 1001L * 60 * 60 * 24 * 3));
        assertThrows(AccountException.class, () -> jwtService.getEmailFromToken("expired,token,test"));
    }


}