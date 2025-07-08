package ch.wiss.m223.project_manager.dto;

import ch.wiss.m223.project_manager.model.Project;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectDTO {
  private Long id;
  private String name;
  private String description;
  private UserDTO owner;

  public static ProjectDTO fromEntity(Project project) {
    if (project == null)
      return null;
    ProjectDTO dto = new ProjectDTO();
    dto.setId(project.getId());
    dto.setName(project.getName());
    dto.setDescription(project.getDescription());
    dto.setOwner(UserDTO.fromEntity(project.getOwner()));
    return dto;
  }
}
