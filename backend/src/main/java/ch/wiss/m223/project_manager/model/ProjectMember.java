package ch.wiss.m223.project_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Repräsentiert die Mitgliedschaft eines Benutzers in einem Projekt.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class ProjectMember {

  /**
   * Die eindeutige ID der Projektmitgliedschaft.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Der Benutzer, der Mitglied im Projekt ist.
   */
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  /**
   * Das Projekt, in dem der Benutzer Mitglied ist.
   */
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  /**
   * Konstruktor zum Erstellen eines Projektmitglieds.
   *
   * @param user Der Benutzer.
   * @param project Das Projekt.
   */
  public ProjectMember(User user, Project project) {
    this.user = user;
    this.project = project;
  }
}
