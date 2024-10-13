package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.request.UserRegistrationRequest;
import com.springboot.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(
      @Valid @RequestBody UserRegistrationRequest request) {
    try {
      userService.registerUser(new UserDTO(request));
      return ResponseEntity.ok("User registered successfully!");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
