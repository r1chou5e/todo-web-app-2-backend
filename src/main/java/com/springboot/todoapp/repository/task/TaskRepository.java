package com.springboot.todoapp.repository.task;

import com.springboot.todoapp.domain.entity.TaskEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

  List<TaskEntity> findTaskEntitiesByTodoListId(Long id);
}
