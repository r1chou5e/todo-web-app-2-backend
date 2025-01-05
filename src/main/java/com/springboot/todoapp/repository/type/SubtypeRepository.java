package com.springboot.todoapp.repository.type;

import com.springboot.todoapp.domain.entity.SubtypeEntity;
import com.springboot.todoapp.domain.entity.TypeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtypeRepository extends CrudRepository<SubtypeEntity, Long> {

  List<SubtypeEntity> findSubtypeEntitiesByType(TypeEntity type);

  @Query("SELECT s FROM SubtypeEntity s WHERE s.type.typeCode = :typeCode AND s.subTypeValue = :subTypeValue")
  SubtypeEntity findSubtypeEntitiesByTypeCodeAndSubTypeValue(String typeCode, String subTypeValue);
}
