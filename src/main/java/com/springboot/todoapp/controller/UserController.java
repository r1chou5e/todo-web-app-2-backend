package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.dto.UserDTO;
import com.springboot.todoapp.domain.request.UserLoginRequest;
import com.springboot.todoapp.domain.request.UserRegistrationRequest;
import com.springboot.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<?> registerUser(
      @Valid @RequestBody UserRegistrationRequest request) {
    userService.registerUser(new UserDTO(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
    String token = userService.login(request.getEmail(), request.getPassword());
    return ResponseEntity.ok().body(token);
  }
}
