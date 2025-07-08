package ch.wiss.m223.project_manager.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Repräsentiert ein Projekt, das Aufgaben und Mitglieder enthalten kann.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Project {

  /**
   * Die eindeutige ID des Projekts.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Der Name des Projekts.
   */
  private String name;

  /**
   * Die Beschreibung des Projekts.
   */
  private String description;

  /**
   * Der Benutzer, der das Projekt erstellt hat (Eigentümer).
   */
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  /**
   * Die Liste der Aufgaben, die zu diesem Projekt gehören.
   */
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
  private List<Task> tasks;
}
