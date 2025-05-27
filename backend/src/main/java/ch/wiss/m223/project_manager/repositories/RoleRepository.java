package ch.wiss.m223.project_manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223.project_manager.model.ERole;
import ch.wiss.m223.project_manager.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(ERole name);

}
