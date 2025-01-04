package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.request.CreateTaskRequest;
import com.springboot.todoapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/")
  public ResponseEntity<?> createNewTask(@Valid @RequestBody CreateTaskRequest request) {
    String message = taskService.addTask(request.toDTO());
    return ResponseEntity.ok(message);
  }
}
