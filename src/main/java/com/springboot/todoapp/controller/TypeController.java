package com.springboot.todoapp.controller;

import com.springboot.todoapp.domain.request.SubtypeCreateRequest;
import com.springboot.todoapp.domain.request.TypeCreateRequest;
import com.springboot.todoapp.domain.response.SubtypesListResponse;
import com.springboot.todoapp.service.TypeService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/type")
public class TypeController {

  @Autowired
  private TypeService typeService;

  @PostMapping("/")
  public ResponseEntity<?> addNewType(@Valid @RequestBody TypeCreateRequest request) {
    String message = typeService.createType(request.toDTO());
    return ResponseEntity.ok(message);
  }

  @PostMapping("/subtype")
  public ResponseEntity<?> addNewSubtype(@Valid @RequestBody SubtypeCreateRequest request) {
    String message = typeService.createSubtype(request.toSubtypeDTO());
    return ResponseEntity.ok(message);
  }

  @GetMapping("/{typeCode}")
  public SubtypesListResponse getSubtypesByTypeCode(@PathVariable String typeCode) {
    val subtypes = typeService.getSubtypesByTypeCode(typeCode);
    return new SubtypesListResponse(subtypes);
  }

  @GetMapping("/subtype/{typeCode}/{subtypeValue}")
  public Long getSubtypeId(@PathVariable String typeCode,
      @PathVariable String subtypeValue) {
    return typeService.getSubtypeIdByTypeCodeAndSubtypeValue(typeCode, subtypeValue);
  }
}
