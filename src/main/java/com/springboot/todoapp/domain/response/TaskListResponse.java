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

    private Long taskId;
    private String title;
    private String description;
    private Long subTypeId;
    private LocalDateTime dueDate;
    private boolean completed;
    private Long todoListId;

    public Task(TaskDTO dto) {
      this.taskId = dto.getTaskId();
      this.title = dto.getTitle();
      this.description = dto.getDescription();
      this.subTypeId = dto.getSubTypeId();
      this.dueDate = dto.getDueDate();
      this.completed = dto.isCompleted();
      this.todoListId = dto.getTodoListId();
    }
  }
}
