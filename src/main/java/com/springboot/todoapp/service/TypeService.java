package com.springboot.todoapp.service;

import com.springboot.todoapp.domain.dto.SubtypeDTO;
import com.springboot.todoapp.domain.dto.TypeDTO;
import com.springboot.todoapp.domain.entity.SubtypeEntity;
import com.springboot.todoapp.domain.entity.TypeEntity;
import com.springboot.todoapp.repository.type.SubtypeRepository;
import com.springboot.todoapp.repository.type.TypeRepository;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

  @Autowired
  private TypeRepository typeRepository;

  @Autowired
  private SubtypeRepository subtypeRepository;

  public String createType(TypeDTO type) {
    TypeEntity typeEntity = new TypeEntity();
    typeEntity.setTypeCode(type.getTypeCode());
    typeEntity.setTypeName(type.getTypeName());
    typeEntity.setDescription(type.getDescription());
    try {
      typeRepository.save(typeEntity);
      return "Create new type successfully";
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String createSubtype(SubtypeDTO subtype) {
    val type = typeRepository.findByTypeCode(subtype.getTypeCode());
    if (type == null) {
      throw new RuntimeException("Type not found");
    }

    SubtypeEntity subtypeEntity = new SubtypeEntity();
    subtypeEntity.setType(type);
    subtypeEntity.setDescription(subtype.getDescription());
    subtypeEntity.setSubTypeName(subtype.getSubtypeName());
    subtypeEntity.setSubTypeValue(subtype.getSubtypeValue());

    try {
      subtypeRepository.save(subtypeEntity);
      return "Create new type successfully";
    } catch (Exception e) {
      throw new RuntimeException("Failed to save subtype entity", e);
    }
  }

  public List<SubtypeDTO> getSubtypesByTypeCode(String typeCode) {
    val type = typeRepository.findByTypeCode(typeCode);
    if (type == null) {
      throw new RuntimeException("Type not found");
    }

    return subtypeRepository.findSubtypeEntitiesByType(type).stream().map(SubtypeDTO::new).toList();
  }

  public Long getSubtypeIdByTypeCodeAndSubtypeValue(String typeCode, String subtypeValue) {
    val subtype = subtypeRepository.findSubtypeEntitiesByTypeCodeAndSubTypeValue(typeCode,
        subtypeValue);
    if (subtype == null) {
      throw new RuntimeException("Subtype not found");
    }
    return subtype.getId();
  }
}
