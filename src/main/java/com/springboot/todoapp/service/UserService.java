package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.ProfileDTO;
import com.springboot.todoapp.repository.token.AccessTokenRepository;
import com.springboot.todoapp.repository.user.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AccessTokenRepository accessTokenRepository;

  public ProfileDTO getUserProfileByAccessToken(String accessToken) {
    val token = accessTokenRepository.findByToken(accessToken);
    if (token != null) {
      val user = userRepository.findById(token.getUserId()).orElse(null);

      if (user != null) {
        return ProfileDTO.builder().username(user.getUsername()).email(user.getEmail()).build();
      }
    }
    return ProfileDTO.builder().build();
  }
}
