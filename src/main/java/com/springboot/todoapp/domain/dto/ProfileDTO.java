package com.springboot.todoapp.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileDTO {

  String username;
  String email;
  String avatarUrl;
  String dob;
}
