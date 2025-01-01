package com.springboot.todoapp.domain.request;

import com.springboot.todoapp.domain.dto.SubtypeDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SubtypeCreateRequest {

  @NotNull
  @Length(max = 100)
  private String subtypeName;

  @NotNull
  private Long subtypeValue;

  @Length(max = 255)
  private String description;

  @NotNull
  @Length(max = 50)
  private String typeCode;

  public SubtypeDTO toSubtypeDTO() {
    return SubtypeDTO.builder().subtypeName(subtypeName).subtypeValue(subtypeValue)
        .description(description).typeCode(typeCode).build();
  }
}
