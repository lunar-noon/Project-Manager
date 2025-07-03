package ch.wiss.m223.project_manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TaskCreateDTO {
  private String title;
  private String description;
  private Date dueDate;
  private Long projectId;
  private Long assignedUserId;
  
}
