package com.springboot.todoapp.domain.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoListDTO {

  private String title;
  private String description;
  private LocalDateTime dueDate;
  
}
