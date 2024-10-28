package com.springboot.todoapp.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {

  @Email
  @Size(max = 30)
  @NotNull
  private String email;

  @NotNull
  @Size(min = 6)
  private String password;
}
