package com.springboot.todoapp.repository.task;

import com.springboot.todoapp.domain.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}
