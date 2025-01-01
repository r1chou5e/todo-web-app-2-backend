package com.springboot.todoapp.repository.type;

import com.springboot.todoapp.domain.entity.SubtypeEntity;
import com.springboot.todoapp.domain.entity.TypeEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtypeRepository extends CrudRepository<SubtypeEntity, Long> {

  List<SubtypeEntity> findSubtypeEntitiesByType(TypeEntity type);
}
