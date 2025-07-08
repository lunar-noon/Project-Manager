package ch.wiss.m223.project_manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.ProjectMember;
import ch.wiss.m223.project_manager.model.User;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
  List<ProjectMember> findByProject(Project project);
  List<ProjectMember> findByUser(User user);
  Optional<ProjectMember> findByProjectAndUser(Project project, User user);
  boolean existsByProjectAndUser(Project project, User user);
}