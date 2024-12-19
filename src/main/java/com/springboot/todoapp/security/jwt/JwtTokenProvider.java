package com.springboot.todoapp.security.jwt;

import com.springboot.todoapp.repository.token.AccessTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Autowired
  AccessTokenRepository accessTokenRepository;

  private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generateToken(String email) {
    return Jwts.builder().setSubject(email).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)).signWith(key).compact();
  }

  public boolean validateToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();

      Date expiration = claims.getExpiration();
      if (expiration == null || expiration.before(new Date())) {
        return false;
      }

      val foundToken = accessTokenRepository.findByToken(token);
      if (foundToken == null || foundToken.isRevoked()) {
        return false;
      }

      if (foundToken.getExpiresAt().isBefore(LocalDateTime.now())) {
        return false;
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}
