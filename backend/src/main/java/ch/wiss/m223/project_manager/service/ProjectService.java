package ch.wiss.m223.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.repositories.ProjectRepository;

/**
 * Service-Klasse für Projekt-Operationen.
 */
@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  /**
   * Gibt alle Projekte zurück.
   *
   * @return Eine Liste aller Projekte.
   */
  @Transactional(readOnly = true)
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  /**
   * Sucht ein Projekt anhand seiner ID.
   *
   * @param id Die ID des Projekts.
   * @return Ein Optional mit dem gefundenen Projekt oder leer, falls nicht vorhanden.
   */
  @Transactional(readOnly = true)
  public Optional<Project> findById(Long id) {
    return projectRepository.findById(id);
  }

  /**
   * Speichert ein Projekt in der Datenbank.
   *
   * @param project Das zu speichernde Projekt.
   * @return Das gespeicherte Projekt.
   */
  @Transactional
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  /**
   * Löscht ein Projekt anhand seiner ID.
   *
   * @param id Die ID des zu löschenden Projekts.
   */
  @Transactional
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }
}
