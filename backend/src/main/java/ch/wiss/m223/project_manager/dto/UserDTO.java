package ch.wiss.m223.project_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private String role;
  
}
