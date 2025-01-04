package com.springboot.todoapp.repository.todolist;

import com.springboot.todoapp.domain.entity.TodoListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends CrudRepository<TodoListEntity, Long> {
  
}
