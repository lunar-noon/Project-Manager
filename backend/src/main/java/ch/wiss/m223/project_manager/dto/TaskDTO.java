package ch.wiss.m223.project_manager.dto;

import java.util.Date;

import ch.wiss.m223.project_manager.model.Task;
import lombok.Getter;
import lombok.Setter;

/**
 * Datenübertragungsobjekt für Aufgaben (Task).
 */
@Setter @Getter
public class TaskDTO {

  private Long id;
  private String title;
  private String description;
  private String status;
  private Date dueDate;
  private Date createdAt;
  private Date updatedAt;

  private UserDTO assignedUser;
  private ProjectDTO project;
  private UserDTO createdBy;

  /**
   * Wandelt ein {@link Task} Entity in ein {@link TaskDTO} um.
   *
   * @param task Das Task-Objekt.
   * @return Ein neues TaskDTO-Objekt oder {@code null}, falls die Aufgabe null ist.
   */
  public static TaskDTO fromEntity(Task task) {
    if (task == null) return null;
    TaskDTO dto = new TaskDTO();
    dto.setId(task.getId());
    dto.setTitle(task.getTitle());
    dto.setDescription(task.getDescription());
    dto.setStatus(task.getStatus().name());
    dto.setAssignedUser(UserDTO.fromEntity(task.getAssignedUser()));
    dto.setCreatedBy(UserDTO.fromEntity(task.getCreatedBy()));
    dto.setProject(ProjectDTO.fromEntity(task.getProject()));
    return dto;
  }
}
