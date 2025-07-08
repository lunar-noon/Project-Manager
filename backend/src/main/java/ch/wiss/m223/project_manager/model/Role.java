package ch.wiss.m223.project_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Repräsentiert eine Rolle, die einem Benutzer zugewiesen werden kann.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Role {

  /**
   * Die eindeutige ID der Rolle.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Der Name der Rolle (als Enum).
   */
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  /**
   * Konstruktor mit Rollenname.
   *
   * @param name Der Rollenname.
   */
  public Role(ERole name) {
    this.name = name;
  }

  /**
   * Gibt den Namen der Rolle als String zurück.
   *
   * @return Der Rollenname.
   */
  @Override
  public String toString() {
    return name.toString();
  }
}
