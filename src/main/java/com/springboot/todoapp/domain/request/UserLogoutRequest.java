package com.springboot.todoapp.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLogoutRequest {

  @NotNull
  private String email;
}
