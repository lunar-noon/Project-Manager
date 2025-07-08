package ch.wiss.m223.project_manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.project_manager.dto.ProjectCreateDTO;
import ch.wiss.m223.project_manager.dto.ProjectDTO;
import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @GetMapping
  public List<ProjectDTO> getAllProjects() {
    return projectService.findAll().stream().map(ProjectDTO::fromEntity).collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<?> createProject(@RequestBody ProjectCreateDTO dto) {
    User owner = userService.findById(dto.getOwnerId()).orElse(null);
    if (owner == null) return ResponseEntity.badRequest().body("Owner nicht gefunden");

    Project project = dto.toEntity(owner);
    Project saved = projectService.save(project);
    return ResponseEntity.ok(ProjectDTO.fromEntity(saved));
  }
}
