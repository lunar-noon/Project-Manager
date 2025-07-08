package ch.wiss.m223.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.UserRepository;

/**
 * Service-Klasse für Benutzer-Operationen.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Sucht einen Benutzer anhand seiner ID.
   *
   * @param id Die ID des Benutzers.
   * @return Ein Optional mit dem gefundenen Benutzer oder leer, falls nicht vorhanden.
   */
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  /**
   * Gibt alle Benutzer zurück.
   *
   * @return Eine Liste aller Benutzer.
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * Speichert einen Benutzer in der Datenbank.
   *
   * @param user Der zu speichernde Benutzer.
   * @return Der gespeicherte Benutzer.
   */
  public User save(User user) {
    return userRepository.save(user);
  }

  /**
   * Löscht einen Benutzer anhand seiner ID.
   *
   * @param id Die ID des zu löschenden Benutzers.
   */
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
