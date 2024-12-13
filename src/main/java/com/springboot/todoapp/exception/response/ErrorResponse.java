package com.springboot.todoapp.exception.response;

import lombok.Data;

@Data
public class ErrorResponse {

  private int status;
  private String message;
  private String timestamp;

  public ErrorResponse(int status, String message, String timestamp) {
    this.status = status;
    this.message = message;
    this.timestamp = timestamp;
  }

}

