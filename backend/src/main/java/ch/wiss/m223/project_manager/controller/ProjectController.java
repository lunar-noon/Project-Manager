package ch.wiss.m223.project_manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.wiss.m223.project_manager.dto.ProjectCreateDTO;
import ch.wiss.m223.project_manager.dto.ProjectDTO;
import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.UserService;

/**
 * REST-Controller zur Verwaltung von Projekten.
 */
@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  /**
   * Gibt alle Projekte im System zurück.
   *
   * @return Liste von {@link ProjectDTO}s.
   */
  @GetMapping
  public List<ProjectDTO> getAllProjects() {
    return projectService.findAll().stream()
        .map(ProjectDTO::fromEntity)
        .collect(Collectors.toList());
  }

  /**
   * Erstellt ein neues Projekt basierend auf einem {@link ProjectCreateDTO}.
   *
   * @param dto Das DTO mit den Eingabedaten.
   * @return Response mit dem erstellten Projekt oder Fehlermeldung.
   */
  @PostMapping
  public ResponseEntity<?> createProject(@RequestBody ProjectCreateDTO dto) {
    User owner = userService.findById(dto.getOwnerId()).orElse(null);
    if (owner == null) {
      return ResponseEntity.badRequest().body("Owner nicht gefunden");
    }

    Project project = dto.toEntity(owner);
    Project saved = projectService.save(project);
    return ResponseEntity.ok(ProjectDTO.fromEntity(saved));
  }
}
