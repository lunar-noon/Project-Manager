package ch.wiss.m223.project_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.ProjectMember;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.ProjectMemberRepository;

@Service
public class ProjectMemberService {

  @Autowired
  private ProjectMemberRepository memberRepository;

  public ProjectMember save(ProjectMember member) {
    return memberRepository.save(member);
  }

  public boolean exists(Project project, User user) {
    return memberRepository.existsByProjectAndUser(project, user);
  }

  public void removeMember(Project project, User user) {
    memberRepository.findByProjectAndUser(project, user).ifPresent(memberRepository::delete);
  }
}