package com.springboot.todoapp.domain.dto;

import com.springboot.todoapp.domain.entity.TaskEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskDTO {

  private String title;
  private String description;
  private Long subTypeId;
  private LocalDateTime dueDate;
  private Long todoListId;

  public static TaskDTO fromEntity(TaskEntity taskEntity) {
    return new TaskDTO(
        taskEntity.getTitle(),
        taskEntity.getDescription(),
        taskEntity.getSubTypeId(),
        taskEntity.getDueDate(),
        taskEntity.getTodoList().getId()
    );
  }
}
