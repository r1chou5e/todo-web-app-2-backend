package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.entity.UserEntity;
import com.springboot.todoapp.domain.request.UserLoginRequest;
import com.springboot.todoapp.domain.request.UserRegistrationRequest;
import com.springboot.todoapp.repository.token.EmailVerificationTokenRepository;
import com.springboot.todoapp.repository.user.UserRepository;
import com.springboot.todoapp.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private EmailVerificationTokenRepository emailVerificationTokenRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
    val email = userService.registerUser(new UserDTO(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(
        "Registered successfully! A verification token has been sent to your email: " + email);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
    String token = userService.login(request.getEmail(), request.getPassword());
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyUser(@RequestParam("token") String token) {
    val foundToken = emailVerificationTokenRepository.findByToken(token);

    if (foundToken.isEmpty() || foundToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
    }

    val user = userRepository.findByEmail(foundToken.get().getEmail());

    if (user.isPresent()) {
      user.get().setActive(true);
      userRepository.save(user.get());
    }

    return ResponseEntity.ok("Account verified successfully.");
  }
}
