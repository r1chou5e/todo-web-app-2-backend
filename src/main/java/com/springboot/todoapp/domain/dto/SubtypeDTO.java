package com.springboot.todoapp.domain.dto;

import com.springboot.todoapp.domain.entity.SubtypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubtypeDTO {

  private String subtypeName;
  private Long subtypeValue;
  private String description;
  private String typeCode;

  public SubtypeDTO(SubtypeEntity entity) {
    subtypeName = entity.getSubTypeName();
    subtypeValue = entity.getSubTypeValue();
    description = entity.getDescription();
    typeCode = entity.getType().getTypeCode();
  }
}
