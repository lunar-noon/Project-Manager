package ch.wiss.m223.project_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProjectDTO {
  private Long id;
  private String name;
  private String description;
  private UserDTO owner;
  
}
