package com.example.healthcar.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

  private final String secret = "healthcar-secret-key-must-be-at-least-32-characters";

  private final long expiration = 3600 * 1000;

  private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

  public String generateToken(Long userId) {

    Date now = new Date();
    Date expiry = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .subject(userId.toString())
        .issuedAt(now)
        .expiration(expiry)
        .signWith(key)
        .compact();
  }

  public long getExpirationSeconds() {
    return expiration / 1000;
  }

  public Long getUserIdFromToken(String token) {

    return Long.valueOf(
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject());
  }
}