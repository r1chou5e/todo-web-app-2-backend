package com.springboot.todoapp.domain.response;

import com.springboot.todoapp.domain.dto.ProfileDTO;
import lombok.Data;

@Data
public class UserProfileResponse {

  private String username;
  private String email;
  private String avataUrl;

  public UserProfileResponse(ProfileDTO profile) {
    username = profile.getUsername();
    email = profile.getEmail();
    avataUrl = profile.getAvatarUrl();
  }
}
