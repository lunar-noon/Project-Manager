package ch.wiss.m223.project_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.ProjectMember;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.ProjectMemberRepository;

/**
 * Service-Klasse für Projektmitglied-Operationen.
 */
@Service
public class ProjectMemberService {

  @Autowired
  private ProjectMemberRepository memberRepository;

  /**
   * Speichert ein Projektmitglied in der Datenbank.
   *
   * @param member Das zu speichernde Projektmitglied.
   * @return Das gespeicherte Projektmitglied.
   */
  public ProjectMember save(ProjectMember member) {
    return memberRepository.save(member);
  }

  /**
   * Überprüft, ob ein Benutzer bereits Mitglied in einem Projekt ist.
   *
   * @param project Das Projekt.
   * @param user Der Benutzer.
   * @return true, wenn der Benutzer bereits Mitglied im Projekt ist, sonst false.
   */
  public boolean exists(Project project, User user) {
    return memberRepository.existsByProjectAndUser(project, user);
  }

  /**
   * Entfernt ein Projektmitglied basierend auf Projekt und Benutzer.
   *
   * @param project Das Projekt.
   * @param user Der Benutzer.
   */
  public void removeMember(Project project, User user) {
    memberRepository.findByProjectAndUser(project, user).ifPresent(memberRepository::delete);
  }
}
