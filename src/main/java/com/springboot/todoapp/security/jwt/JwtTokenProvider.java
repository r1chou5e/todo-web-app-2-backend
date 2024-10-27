package com.springboot.todoapp.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  private final long jwtExpirationMs = 86400000;

  public String generateToken(String email) {
    return Jwts.builder().setSubject(email).setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
        .signWith(key).compact();
  }
}
