package com.springboot.todoapp.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeDTO {

  private String typeCode;
  private String typeName;
  private String description;
}
