package com.springboot.todoapp.domain.dto;

import com.springboot.todoapp.domain.request.UserRegistrationRequest;
import lombok.Data;

@Data
public class UserDTO {

  private String username;

  private String password;

  private String email;

  public UserDTO(UserRegistrationRequest userRegistrationRequest) {
    this.username = userRegistrationRequest.getUsername();
    this.password = userRegistrationRequest.getPassword();
    this.email = userRegistrationRequest.getEmail();
  }
}
