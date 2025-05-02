package com.test.diego.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long validityMs;

    public JwtTokenProvider(
        @Value("${app.jwt.secret}") String secret,
        @Value("${app.jwt.validity}") long validityMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityMs = validityMs;
    }

    public String generateToken(String username, List<String> roles, Long userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        claims.put("userId", userId);

        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMs);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token)
            .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
