package ch.wiss.m223.project_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.TaskRepository;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public List<Task> findByProject(Project project) {
    return taskRepository.findByProject(project);
  }

  public List<Task> findByAssignedUser(User user) {
    return taskRepository.findByAssignedUser(user);
  }

  public Task save(Task task) {
    return taskRepository.save(task);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }
}
