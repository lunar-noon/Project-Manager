package ch.wiss.m223.project_manager.dto;

import java.util.Date;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.Task;
import ch.wiss.m223.project_manager.model.TaskStatus;
import ch.wiss.m223.project_manager.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskCreateDTO {
  private String title;
  private String description;
  private String status;
  private Date dueDate;
  private Long projectId;
  private Long createdById;
  private Long assignedUserId;

  public Task toEntity(User assignedUser, User createdBy, Project project) {
    Task task = new Task();
    task.setTitle(this.title);
    task.setDescription(this.description);
    task.setStatus(TaskStatus.valueOf(this.status)); // Enum parsen
    task.setAssignedUser(assignedUser);
    task.setCreatedBy(createdBy);
    task.setProject(project);
    return task;
  }
}
