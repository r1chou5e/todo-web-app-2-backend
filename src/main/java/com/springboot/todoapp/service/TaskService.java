package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.TaskDTO;
import com.springboot.todoapp.domain.entity.TaskEntity;
import com.springboot.todoapp.repository.task.TaskRepository;
import com.springboot.todoapp.repository.todolist.TodoListRepository;
import com.springboot.todoapp.repository.type.SubtypeRepository;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private SubtypeRepository subtypeRepository;

  @Autowired
  private TodoListRepository todoListRepository;

  @Autowired
  private TaskRepository taskRepository;

  public String addTask(TaskDTO task) {

    val foundSubtype = subtypeRepository.findById(task.getSubTypeId());

    if (foundSubtype.isEmpty()) {
      throw new RuntimeException("Subtype not found");
    }

    val todoList = todoListRepository.findById(task.getTodoListId());

    if (todoList.isEmpty()) {
      throw new RuntimeException("Todo-list not found");
    }

    TaskEntity entity = new TaskEntity();
    entity.setTitle(task.getTitle());
    entity.setDescription(task.getDescription());
    entity.setSubTypeId(task.getSubTypeId());
    entity.setDueDate(task.getDueDate());
    entity.setTodoList(todoList.get());
    entity.setCompleted(false);

    try {
      taskRepository.save(entity);
      return "Create new task successfully";
    } catch (Exception e) {
      throw new RuntimeException("Cannot insert task");
    }
  }

  public List<TaskDTO> getTasksByTodoList(Long id) {
    val taskList = taskRepository.findTaskEntitiesByTodoListId(id);
    return taskList.stream().map(TaskDTO::fromEntity).toList();
  }
}
