package ch.wiss.m223.project_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.repositories.ProjectRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

  private final ProjectRepository projectRepository;

  public ProjectController(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @GetMapping
  public ResponseEntity<List<Project>> getAllProjects() {
    return ResponseEntity.ok(projectRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Project> addProject(@RequestBody Project project) {
    Project savedProject = projectRepository.save(project);
    return ResponseEntity.ok(savedProject);
  }
  
}
