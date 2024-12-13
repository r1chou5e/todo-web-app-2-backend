package com.springboot.todoapp.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginProfileDTO {

  String username;
  String email;
  String avatarUrl;
  String dob;
  boolean isExpired;
}
