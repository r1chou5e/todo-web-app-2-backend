package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.request.CreateTodoListRequest;
import com.springboot.todoapp.service.TodoListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo-list")
public class TodoListController {

  @Autowired
  private TodoListService todoListService;

  @PostMapping("/")
  public ResponseEntity<?> createNewTodoList(@Valid @RequestBody CreateTodoListRequest request) {
    String message = todoListService.addTodoList(request.toDTO());
    return ResponseEntity.ok(message);
  }
}
