package com.springboot.todoapp.domain.request;

import com.springboot.todoapp.domain.dto.TypeDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TypeCreateRequest {

  @NotNull
  @Length(max = 50)
  private String typeCode;

  @NotNull
  @Length(max = 100)
  private String typeName;

  @Length(max = 255)
  private String description;

  public TypeDTO toDTO() {
    return TypeDTO.builder().typeCode(typeCode).typeName(typeName).description(description).build();
  }
}
