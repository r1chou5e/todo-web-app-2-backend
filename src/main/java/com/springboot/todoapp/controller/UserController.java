package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.response.LoginProfileResponse;
import com.springboot.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/get-profile-by-access-token")
  public LoginProfileResponse getUserProfileByAccessToken(
      @Valid @RequestHeader("Authorization") String accessToken) {
    return new LoginProfileResponse(userService.getUserProfileByAccessToken(accessToken));
  }
}
