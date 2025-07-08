package ch.wiss.m223.project_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.TaskStatus;
import ch.wiss.m223.project_manager.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByProject(Project project);
  List<Task> findByAssignedUser(User user);
  List<Task> findByStatus(TaskStatus status);
}
