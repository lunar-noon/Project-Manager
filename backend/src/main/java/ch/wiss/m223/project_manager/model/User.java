package ch.wiss.m223.project_manager.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Repräsentiert einen Benutzer im System.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class User {

  /**
   * Die eindeutige ID des Benutzers.
   */
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  /**
   * Der Benutzername des Benutzers (darf nicht leer sein).
   */
  @NotBlank
  private String username;

  /**
   * Das Passwort des Benutzers (darf nicht leer sein).
   */
  @NotBlank
  private String password;

  /**
   * Die E-Mail-Adresse des Benutzers (darf nicht leer sein).
   */
  @NotBlank
  private String email;

  /**
   * Die Rollen des Benutzers (z. B. ADMIN, USER).
   */
  @ManyToMany(fetch = jakarta.persistence.FetchType.LAZY)
  private Set<Role> roles = new HashSet<>();

  /**
   * Konstruktor zum Erstellen eines Benutzers mit Pflichtfeldern.
   *
   * @param username Der Benutzername.
   * @param password Das Passwort.
   * @param email Die E-Mail-Adresse.
   */
  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}
