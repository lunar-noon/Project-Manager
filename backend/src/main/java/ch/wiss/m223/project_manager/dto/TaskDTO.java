package ch.wiss.m223.project_manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TaskDTO {
  private Long id;
  private String title;
  private String description;
  private String status;
  private Date dueDate;
  private Date createdAt;
  private Date updatedAt;
  
  private UserDTO assignedUser;
  private ProjectDTO project;
  private UserDTO createdBy;

}
