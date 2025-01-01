package com.springboot.todoapp.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "types")
@Data
@Entity
@NoArgsConstructor
public class TypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type_code", nullable = false, unique = true, length = 50)
  private String typeCode;

  @Column(name = "type_name", nullable = false, length = 100)
  private String typeName;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SubtypeEntity> subTypes = new ArrayList<>();
}
