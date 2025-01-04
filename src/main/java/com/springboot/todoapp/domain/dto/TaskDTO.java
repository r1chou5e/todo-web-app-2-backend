package com.springboot.todoapp.domain.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDTO {

  private String title;
  private String description;
  private Long subTypeId;
  private LocalDateTime dueDate;
  private Long todoListId;
}
