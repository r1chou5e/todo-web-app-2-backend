package com.springboot.todoapp.domain.response;

import com.springboot.todoapp.domain.dto.TaskDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class TaskListResponse {

  List<Task> taskList;

  public TaskListResponse(List<TaskDTO> dtos) {
    taskList = dtos.stream().map(Task::new).toList();
  }

  @Data
  public static class Task {

    private String title;
    private String description;
    private Long subTypeId;
    private LocalDateTime dueDate;
    private Long todoListId;

    public Task(TaskDTO dto) {
      this.title = dto.getTitle();
      this.description = dto.getDescription();
      this.subTypeId = dto.getSubTypeId();
      this.dueDate = dto.getDueDate();
      this.todoListId = dto.getTodoListId();
    }
  }
}
