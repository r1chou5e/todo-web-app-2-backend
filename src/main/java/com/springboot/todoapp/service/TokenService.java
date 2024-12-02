package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.entity.AccessTokenEntity;
import com.springboot.todoapp.domain.entity.EmailVerificationTokenEntity;
import com.springboot.todoapp.repository.token.AccessTokenRepository;
import com.springboot.todoapp.repository.token.EmailVerificationTokenRepository;
import com.springboot.todoapp.security.jwt.JwtTokenProvider;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final int EMAIL_VERIFICATION_TOKEN_EXPIRY_HOURS = 1;
  private static final int ACCESS_TOKEN_EXPIRY_HOURS = 24;

  @Autowired
  private AccessTokenRepository accessTokenRepository;
  @Autowired
  private EmailVerificationTokenRepository emailVerificationTokenRepository;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  public String createVerficationToken(String email) {

    String token;
    do {
      token = UUID.randomUUID().toString();
    } while (emailVerificationTokenRepository.existsByToken(token));

    EmailVerificationTokenEntity tokenEntity = new EmailVerificationTokenEntity();

    tokenEntity.setEmail(email);
    tokenEntity.setExpiryDate(LocalDateTime.now().plusHours(EMAIL_VERIFICATION_TOKEN_EXPIRY_HOURS));
    tokenEntity.setToken(token);

    emailVerificationTokenRepository.save(tokenEntity);

    return token;
  }

  public String createAccessToken(Long userId, String email) {

    String token;

    do {
      token = jwtTokenProvider.generateToken(email);
    } while (accessTokenRepository.existsByToken(token));

    AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
    accessTokenEntity.setToken(token);
    accessTokenEntity.setUserId(userId);
    accessTokenEntity.setCreatedAt((LocalDateTime.now()));
    accessTokenEntity.setExpiresAt(LocalDateTime.now().plusHours(ACCESS_TOKEN_EXPIRY_HOURS));
    accessTokenEntity.setRevoked(false);

    accessTokenRepository.save(accessTokenEntity);

    return token;
  }
}
