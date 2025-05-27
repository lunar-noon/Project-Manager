package ch.wiss.m223.project_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.wiss.m223.project_manager.model.Role;
import ch.wiss.m223.project_manager.model.ERole;
import ch.wiss.m223.project_manager.repositories.RoleRepository;

@SpringBootApplication
public class ProjectManagerApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
	}
}
