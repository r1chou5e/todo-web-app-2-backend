package com.springboot.todoapp.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sub_types")
public class SubtypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sub_type_value", nullable = false, length = 15)
  private Long subTypeValue;

  @Column(name = "sub_type_name", nullable = false, length = 100)
  private String subTypeName;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private TypeEntity type;
}
