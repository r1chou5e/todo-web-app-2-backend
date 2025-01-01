package com.springboot.todoapp.domain.response;

import com.springboot.todoapp.domain.dto.SubtypeDTO;
import java.util.List;
import lombok.Data;

@Data
public class SubtypesListResponse {

  List<Subtype> subtypes;

  public SubtypesListResponse(List<SubtypeDTO> subtypeDTOs) {
    subtypes = subtypeDTOs.stream().map(Subtype::new).toList();
  }

  @Data
  public static class Subtype {

    private String subtypeName;
    private Long subtypeValue;
    private String description;

    public Subtype(SubtypeDTO subtypeDTO) {
      this.subtypeName = subtypeDTO.getSubtypeName();
      this.subtypeValue = subtypeDTO.getSubtypeValue();
      this.description = subtypeDTO.getDescription();
    }
  }
}
