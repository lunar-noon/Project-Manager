package ch.wiss.m223.project_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.ProjectMember;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectMemberService;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.UserService;

@RestController
@RequestMapping("/project-members")
@CrossOrigin
public class ProjectMemberController {
  @Autowired
  private ProjectMemberService memberService;
  @Autowired
  private ProjectService projectService;
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> addMember(@RequestParam Long projectId, @RequestParam Long userId) {
    Project project = projectService.findById(projectId).orElse(null);
    User user = userService.findById(userId).orElse(null);

    if (project == null || user == null) {
      return ResponseEntity.badRequest().body("Projekt oder Nutzer nicht gefunden");
    }

    if (memberService.exists(project, user)) {
      return ResponseEntity.badRequest().body("Nutzer ist bereits Mitglied");
    }

    ProjectMember member = new ProjectMember(user, project);
    memberService.save(member);

    return ResponseEntity.ok("Mitglied hinzugefügt");
  }

  @DeleteMapping
  public ResponseEntity<?> removeMember(@RequestParam Long projectId, @RequestParam Long userId) {
    Project project = projectService.findById(projectId).orElse(null);
    User user = userService.findById(userId).orElse(null);

    if (project == null || user == null) {
      return ResponseEntity.badRequest().body("Projekt oder Nutzer nicht gefunden");
    }

    memberService.removeMember(project, user);
    return ResponseEntity.ok("Mitglied entfernt");
  }
}
