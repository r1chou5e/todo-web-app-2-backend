package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.LoginProfileDTO;
import com.springboot.todoapp.repository.token.AccessTokenRepository;
import com.springboot.todoapp.repository.user.UserRepository;
import com.springboot.todoapp.utils.TokenUtils;
import java.time.LocalDateTime;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AccessTokenRepository accessTokenRepository;

  public LoginProfileDTO getUserProfileByAccessToken(String accessToken) {
    String rawToken = TokenUtils.getRawTokenWithoutBearer(accessToken);
    val token = accessTokenRepository.findByToken(rawToken);
    if (token != null) {

      val user = userRepository.findById(token.getUserId()).orElse(null);

      if (user != null) {
        return LoginProfileDTO.builder().username(user.getUsername()).email(user.getEmail())
            .build();
      }
    }
    return LoginProfileDTO.builder().build();
  }
}
