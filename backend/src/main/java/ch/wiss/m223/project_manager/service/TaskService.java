package ch.wiss.m223.project_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.TaskRepository;

/**
 * Service-Klasse für Aufgaben-Operationen.
 */
@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  /**
   * Gibt alle Aufgaben zurück.
   *
   * @return Eine Liste aller Aufgaben.
   */
  @Transactional(readOnly = true)
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  /**
   * Gibt alle Aufgaben eines bestimmten Projekts zurück.
   *
   * @param project Das Projekt.
   * @return Eine Liste der Aufgaben für das gegebene Projekt.
   */
  @Transactional(readOnly = true)
  public List<Task> findByProject(Project project) {
    return taskRepository.findByProject(project);
  }

  /**
   * Gibt alle Aufgaben eines bestimmten Benutzers zurück.
   *
   * @param user Der Benutzer.
   * @return Eine Liste der Aufgaben, die dem Benutzer zugewiesen sind.
   */
  @Transactional(readOnly = true)
  public List<Task> findByAssignedUser(User user) {
    return taskRepository.findByAssignedUser(user);
  }

  /**
   * Speichert eine Aufgabe in der Datenbank.
   *
   * @param task Die zu speichernde Aufgabe.
   * @return Die gespeicherte Aufgabe.
   */
  @Transactional
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  /**
   * Löscht eine Aufgabe anhand ihrer ID.
   *
   * @param id Die ID der zu löschenden Aufgabe.
   */
  @Transactional
  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }
}
