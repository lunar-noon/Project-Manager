package ch.wiss.m223.project_manager.dto;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO zur Erstellung eines neuen Projekts.
 */
@Setter @Getter
public class ProjectCreateDTO {

  /**
   * Der Name des Projekts.
   */
  private String name;

  /**
   * Die Beschreibung des Projekts.
   */
  private String description;

  /**
   * Die ID des Projektinhabers.
   */
  private Long ownerId;

  /**
   * Konvertiert das DTO in ein {@link Project}-Entity.
   *
   * @param owner Der Benutzer, der das Projekt erstellt.
   * @return Ein {@link Project} mit den übergebenen Attributen.
   */
  public Project toEntity(User owner) {
    Project p = new Project();
    p.setName(name);
    p.setDescription(description);
    p.setOwner(owner);
    return p;
  }
}
