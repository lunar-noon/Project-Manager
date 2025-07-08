package ch.wiss.m223.project_manager.dto;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProjectCreateDTO {
  private String name;
  private String description;
  private Long ownerId;

  public Project toEntity(User owner) {
    Project p = new Project();
    p.setName(name);
    p.setDescription(description);
    p.setOwner(owner);
    return p;
  }
}
