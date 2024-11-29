package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.entity.EmailVerificationTokenEntity;
import com.springboot.todoapp.repository.token.EmailVerificationTokenRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Autowired
  private EmailVerificationTokenRepository emailVerificationTokenRepository;

  public String createVerficationToken(
      String email) {

    String token;
    do {
      token = UUID.randomUUID().toString();
    } while (emailVerificationTokenRepository.existsByToken(token));

    EmailVerificationTokenEntity tokenEntity = new EmailVerificationTokenEntity();

    tokenEntity.setEmail(email);
    tokenEntity.setExpiryDate(LocalDateTime.now().plusHours(24));
    tokenEntity.setToken(token);

    emailVerificationTokenRepository.save(tokenEntity);

    return token;
  }
}
