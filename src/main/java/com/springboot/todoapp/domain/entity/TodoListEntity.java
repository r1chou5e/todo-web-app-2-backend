package com.springboot.todoapp.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "todo_lists")
@Entity
@Data
@NoArgsConstructor
public class TodoListEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", length = 150, nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(nullable = false)
  private boolean completed;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TaskEntity> tasks = new ArrayList<>();
}
