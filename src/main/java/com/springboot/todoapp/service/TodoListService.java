package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.TodoListDTO;
import com.springboot.todoapp.domain.entity.TodoListEntity;
import com.springboot.todoapp.repository.todolist.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

  @Autowired
  private TodoListRepository todoListRepository;

  public String addTodoList(TodoListDTO todoListDTO) {
    TodoListEntity entity = new TodoListEntity();
    entity.setTitle(todoListDTO.getTitle());
    entity.setDescription(todoListDTO.getDescription());
    entity.setCompleted(false);
    entity.setDueDate(todoListDTO.getDueDate());

    try {
      todoListRepository.save(entity);
      return "Create new todo-list successfully";
    } catch (Exception e) {
      throw new RuntimeException("Cannot insert todo-list");
    }
  }
}
