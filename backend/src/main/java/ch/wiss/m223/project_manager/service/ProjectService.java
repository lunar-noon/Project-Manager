package ch.wiss.m223.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.repositories.ProjectRepository;

@Service
public class ProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;

  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  public Optional<Project> findById(Long id) {
    return projectRepository.findById(id);
  }

  public Project save(Project project) {
    return projectRepository.save(project);
  }

  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }
}