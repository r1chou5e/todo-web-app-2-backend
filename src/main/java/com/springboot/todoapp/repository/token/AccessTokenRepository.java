package com.springboot.todoapp.repository.token;

import com.springboot.todoapp.domain.entity.AccessTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessTokenEntity, String> {

  AccessTokenEntity findAccessTokenEntitiesByUserId(Long userId);

  boolean existsByToken(String token);

  AccessTokenEntity findByToken(String token);
}
