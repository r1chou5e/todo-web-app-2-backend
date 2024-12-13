package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.response.LoginProfileResponse;
import com.springboot.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{token}")
  public LoginProfileResponse getUserProfileByAccessToken(@PathVariable String token) {
    return new LoginProfileResponse(userService.getUserProfileByAccessToken(token));
  }
}
