package com.springboot.todoapp.repository.token;

import com.springboot.todoapp.domain.entity.EmailVerificationTokenEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenRepository extends
    CrudRepository<EmailVerificationTokenEntity, String> {

  Optional<EmailVerificationTokenEntity> findByToken(String token);

  boolean existsByToken(String token);
}
