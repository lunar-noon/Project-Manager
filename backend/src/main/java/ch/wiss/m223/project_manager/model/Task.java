package ch.wiss.m223.project_manager.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  private Date dueDate;
  private Date createdAt;
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "assigned_user_id")
  private User assignedUser;

  @ManyToOne
  @JoinColumn(name = "created_by_id")
  private User createdBy;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;
}
