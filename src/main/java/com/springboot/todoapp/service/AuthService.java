package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.entity.UserEntity;
import com.springboot.todoapp.repository.token.AccessTokenRepository;
import com.springboot.todoapp.repository.token.EmailVerificationTokenRepository;
import com.springboot.todoapp.repository.user.UserRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmailVerificationTokenRepository emailVerificationTokenRepository;

  @Autowired
  private AccessTokenRepository accessTokenRepository;


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
    user.setActive(false);

    val newUser = userRepository.save(user);

    String token = tokenService.createVerficationToken(newUser.getEmail());

    emailService.sendVerificationEmail(newUser.getEmail(), token);

    return newUser.getEmail();
  }

  public String login(String email, String password) {
    UserEntity user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Invalid email or password!"));

    if (!user.getActive()) {
      throw new RuntimeException("User is not active!");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid email or password!");
    }

    String token = tokenService.createAccessToken(user.getId(), user.getEmail());

    return "Bearer " + token;
  }

  public String logout(String email, String accessToken) {
    String token = accessToken.startsWith("Bearer ") ? accessToken.substring(7) : accessToken;
    val foundToken = accessTokenRepository.findByToken(token);
    val user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new RuntimeException("User not found!");
    }
    if (!Objects.equals(foundToken.getUserId(), user.get().getId())) {
      throw new RuntimeException("Invalid access token!");
    }
    
    foundToken.setRevoked(true);
    accessTokenRepository.save(foundToken);

    return "Logout successfully";
  }

  public String verifyUser(String token) {
    val foundToken = emailVerificationTokenRepository.findByToken(token);

    if (foundToken.isEmpty() || foundToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Invalid or expired token");
    }

    val user = userRepository.findByEmail(foundToken.get().getEmail());

    if (user.isPresent()) {
      user.get().setActive(true);
      userRepository.save(user.get());
    } else {
      throw new RuntimeException("User not found");
    }

    return "Account verified successfully.";
  }
}
