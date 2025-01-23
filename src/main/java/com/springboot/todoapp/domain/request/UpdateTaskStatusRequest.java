package com.springboot.todoapp.domain.request;

import lombok.Data;

@Data
public class UpdateTaskStatusRequest {

  private boolean completed;
}
