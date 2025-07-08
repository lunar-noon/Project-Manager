package ch.wiss.m223.project_manager.dto;

import ch.wiss.m223.project_manager.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Datenübertragungsobjekt für Benutzer (User).
 */
@Setter @Getter
public class UserDTO {

  /**
   * Die ID des Benutzers.
   */
  private Long id;

  /**
   * Der Benutzername.
   */
  private String username;

  /**
   * Die E-Mail-Adresse.
   */
  private String email;

  /**
   * Die Rolle als String.
   */
  private String role;

  /**
   * Wandelt ein {@link User} Entity in ein {@link UserDTO} um.
   *
   * @param user Das User-Objekt.
   * @return Ein neues UserDTO-Objekt oder {@code null}, falls der Benutzer null ist.
   */
  public static UserDTO fromEntity(User user) {
    if (user == null) return null;
    UserDTO dto = new UserDTO();
    dto.setId(user.getId());
    dto.setUsername(user.getUsername());
    dto.setEmail(user.getEmail());
    return dto;
  }
}
