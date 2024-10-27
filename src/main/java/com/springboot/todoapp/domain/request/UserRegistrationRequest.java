package com.springboot.todoapp.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {

  @NotNull
  @Size(min = 4, max = 20)
  private String username;

  @NotNull
  @Size(min = 6)
  private String password;

  @Email
  @Size(max = 30)
  @NotNull
  private String email;
}
