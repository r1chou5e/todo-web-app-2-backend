package com.springboot.todoapp.domain.request;

import com.springboot.todoapp.domain.dto.TaskDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTaskRequest {

  @NotNull
  @Length(max = 150)
  private String title;

  private String description;

  @NotNull
  private Long subTypeId;

  private LocalDateTime dueDate;

  @NotNull
  private Long todoListId;

  public TaskDTO toDTO() {
    return TaskDTO.builder().title(title).description(description).subTypeId(subTypeId)
        .dueDate(dueDate).todoListId(todoListId).build();
  }
}
