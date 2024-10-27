package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.entity.UserEntity;
import com.springboot.todoapp.repository.user.UserRepository;
import com.springboot.todoapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  public String registerUser(UserDTO request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new RuntimeException("Username is already taken!");
    }
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email is already taken!");
    }

    UserEntity user = new UserEntity();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setEmail(request.getEmail());

    userRepository.save(user);

    return jwtTokenProvider.generateToken(request.getEmail());
  }
}
