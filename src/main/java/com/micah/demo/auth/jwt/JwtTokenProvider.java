package com.micah.demo.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final static String jwtSecret = "test1234567890abcdefghijklmopqrstuvwxyz12345678901234567890!test";
    private final static Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    private final static Duration expiration = Duration.ofHours(2);

    public static String generateToken(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
