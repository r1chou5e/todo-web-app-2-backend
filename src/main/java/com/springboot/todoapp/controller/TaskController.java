package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.request.CreateTaskRequest;
import com.springboot.todoapp.domain.request.UpdateTaskStatusRequest;
import com.springboot.todoapp.domain.response.TaskListResponse;
import com.springboot.todoapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("")
  public ResponseEntity<?> createNewTask(@Valid @RequestBody CreateTaskRequest request) {
    String message = taskService.addTask(request.toDTO());
    return ResponseEntity.ok(message);
  }

  @GetMapping("/{todoListId}")
  public TaskListResponse getTasksByTodoListId(@Valid @PathVariable Long todoListId) {
    val taskList = taskService.getTasksByTodoList(todoListId);
    return new TaskListResponse(taskList);
  }

  @PutMapping("/{taskId}/status")
  public ResponseEntity<?> updateCompleteTask(@Valid @PathVariable Long taskId,
      @Valid @RequestBody UpdateTaskStatusRequest request) {
    String message = taskService.updateTaskStatus(taskId, request.isCompleted());
    return ResponseEntity.ok(message);
  }
}
