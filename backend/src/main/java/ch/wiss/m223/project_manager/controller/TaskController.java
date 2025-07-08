package ch.wiss.m223.project_manager.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.wiss.m223.project_manager.dto.TaskCreateDTO;
import ch.wiss.m223.project_manager.dto.TaskDTO;
import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.TaskService;
import ch.wiss.m223.project_manager.service.UserService;

/**
 * REST-Controller zur Verwaltung von Aufgaben.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  /**
   * Gibt eine Liste aller Aufgaben zurück.
   *
   * @return Eine Liste von {@link TaskDTO}s.
   */
  @GetMapping
  public List<TaskDTO> getAllTasks() {
    return taskService.findAll().stream()
        .map(TaskDTO::fromEntity)
        .collect(Collectors.toList());
  }

  /**
   * Erstellt eine neue Aufgabe basierend auf einem {@link TaskCreateDTO}.
   *
   * @param dto Die DTO-Daten für die neue Aufgabe.
   * @return Response mit der erstellten Aufgabe oder einer Fehlermeldung.
   */
  @PostMapping
  public ResponseEntity<?> createTask(@RequestBody TaskCreateDTO dto) {
    Optional<User> assignedUserOpt = userService.findById(dto.getAssignedUserId());
    Optional<User> createdByOpt = userService.findById(dto.getCreatedById());
    Optional<Project> projectOpt = projectService.findById(dto.getProjectId());

    if (assignedUserOpt.isEmpty() || createdByOpt.isEmpty() || projectOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Benutzer oder Projekt nicht gefunden");
    }

    Task task = dto.toEntity(
        assignedUserOpt.get(),
        createdByOpt.get(),
        projectOpt.get());

    Task saved = taskService.save(task);
    return ResponseEntity.ok(TaskDTO.fromEntity(saved));
  }
}
