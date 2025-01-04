package com.springboot.todoapp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tasks")
@Entity
@Data
@NoArgsConstructor
public class TaskEntity {

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

  @ManyToOne
  @JoinColumn(name = "todo_list_id", nullable = false)
  private TodoListEntity todoList;
}
