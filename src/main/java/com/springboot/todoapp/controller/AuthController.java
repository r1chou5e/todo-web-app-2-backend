package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.request.UserLoginRequest;
import com.springboot.todoapp.domain.request.UserLogoutRequest;
import com.springboot.todoapp.domain.request.UserRegistrationRequest;
import com.springboot.todoapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
    val email = authService.registerUser(new UserDTO(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(
        "Registered successfully! A verification token has been sent to your email: " + email);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
    String token = authService.login(request.getEmail(), request.getPassword());
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyUser(@RequestParam("token") String confirmationToken) {
    String message = authService.verifyUser(confirmationToken);
    return ResponseEntity.ok(message);
  }

  @PutMapping("/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestHeader("Authorization") String accessToken,
      @Valid @RequestBody UserLogoutRequest request) {
    String message = authService.logout(request.getEmail(), accessToken);
    return ResponseEntity.ok(message);
  }
}
