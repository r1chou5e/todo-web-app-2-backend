package com.springboot.todoapp.repository.user;

import com.springboot.todoapp.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
