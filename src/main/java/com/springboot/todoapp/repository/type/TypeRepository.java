package com.springboot.todoapp.repository.type;

import com.springboot.todoapp.domain.entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<TypeEntity, Long> {

  TypeEntity findByTypeCode(String typeCode);
}
