package ch.wiss.m223.project_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.ProjectMember;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.service.ProjectMemberService;
import ch.wiss.m223.project_manager.service.ProjectService;
import ch.wiss.m223.project_manager.service.UserService;

/**
 * REST-Controller zur Verwaltung von Projektmitgliedschaften.
 */
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

  /**
   * Fügt einem Projekt ein Mitglied hinzu.
   *
   * @param projectId Die ID des Projekts.
   * @param userId    Die ID des Benutzers.
   * @return Response mit Erfolg oder Fehlermeldung.
   */
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

  /**
   * Entfernt ein Mitglied aus einem Projekt.
   *
   * @param projectId Die ID des Projekts.
   * @param userId    Die ID des Benutzers.
   * @return Response mit Erfolg oder Fehlermeldung.
   */
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
