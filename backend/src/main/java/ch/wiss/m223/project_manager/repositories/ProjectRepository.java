package ch.wiss.m223.project_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223.project_manager.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  
}
