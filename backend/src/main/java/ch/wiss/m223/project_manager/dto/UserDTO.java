package ch.wiss.m223.project_manager.dto;

import ch.wiss.m223.project_manager.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private String role;
  
  public static UserDTO fromEntity(User user) {
    if (user == null) return null;
    UserDTO dto = new UserDTO();
    dto.setId(user.getId());
    dto.setUsername(user.getUsername());
    dto.setEmail(user.getEmail());
    return dto;
  }
}
