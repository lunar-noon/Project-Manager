package ch.wiss.m223.project_manager.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.project_manager.dto.TaskCreateDTO;
import ch.wiss.m223.project_manager.dto.TaskDTO;
import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.TaskService;
import ch.wiss.m223.project_manager.service.UserService;

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

  @GetMapping
  public List<TaskDTO> getAllTasks() {
    return taskService.findAll().stream()
        .map(TaskDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<?> createTask(@RequestBody TaskCreateDTO dto) {
    // Nutzer und Projekt aus DTO-IDs holen
    Optional<User> assignedUserOpt = userService.findById(dto.getAssignedUserId());
    Optional<User> createdByOpt = userService.findById(dto.getCreatedById());
    Optional<Project> projectOpt = projectService.findById(dto.getProjectId());

    // Validierung
    if (assignedUserOpt.isEmpty() || createdByOpt.isEmpty() || projectOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Benutzer oder Projekt nicht gefunden");
    }

    // Entity aus DTO erstellen
    Task task = dto.toEntity(
        assignedUserOpt.get(),
        createdByOpt.get(),
        projectOpt.get());

    Task saved = taskService.save(task);
    return ResponseEntity.ok(TaskDTO.fromEntity(saved));
  }
}
