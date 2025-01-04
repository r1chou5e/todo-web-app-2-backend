package com.springboot.todoapp.domain.request;

import com.springboot.todoapp.domain.dto.TodoListDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTodoListRequest {

  @NotNull
  @Length(max = 150)
  private String title;

  private String description;

  private LocalDateTime dueDate;

  public TodoListDTO toDTO() {
    return TodoListDTO.builder().title(title).description(description).dueDate(dueDate).build();
  }
}
