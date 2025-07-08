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

/**
 * Repräsentiert eine Aufgabe innerhalb eines Projekts.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Task {

  /**
   * Die eindeutige ID der Aufgabe.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Der Titel der Aufgabe.
   */
  private String title;

  /**
   * Die Beschreibung der Aufgabe.
   */
  private String description;

  /**
   * Der Status der Aufgabe (OPEN, IN_PROGRESS, DONE).
   */
  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  /**
   * Das Fälligkeitsdatum der Aufgabe.
   */
  private Date dueDate;

  /**
   * Das Erstellungsdatum der Aufgabe.
   */
  private Date createdAt;

  /**
   * Das letzte Änderungsdatum der Aufgabe.
   */
  private Date updatedAt;

  /**
   * Der Benutzer, dem die Aufgabe zugewiesen ist.
   */
  @ManyToOne
  @JoinColumn(name = "assigned_user_id")
  private User assignedUser;

  /**
   * Der Benutzer, der die Aufgabe erstellt hat.
   */
  @ManyToOne
  @JoinColumn(name = "created_by_id")
  private User createdBy;

  /**
   * Das zugehörige Projekt der Aufgabe.
   */
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;
}
