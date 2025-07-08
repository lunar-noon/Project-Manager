package ch.wiss.m223.project_manager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO zur Repräsentation eines Projektmitglieds (nur IDs).
 */
@Setter @Getter
public class ProjectMemberDTO {

  /**
   * Die ID des Benutzers.
   */
  private Long userId;

  /**
   * Die ID des Projekts.
   */
  private Long projectId;
}
