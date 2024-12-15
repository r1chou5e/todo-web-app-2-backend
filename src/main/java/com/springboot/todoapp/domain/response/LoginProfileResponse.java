package com.springboot.todoapp.domain.response;

import com.springboot.todoapp.domain.dto.LoginProfileDTO;
import lombok.Data;

@Data
public class LoginProfileResponse {

  private String username;
  private String email;
  private String avataUrl;

  public LoginProfileResponse(LoginProfileDTO profile) {
    username = profile.getUsername();
    email = profile.getEmail();
    avataUrl = profile.getAvatarUrl();
  }
}
