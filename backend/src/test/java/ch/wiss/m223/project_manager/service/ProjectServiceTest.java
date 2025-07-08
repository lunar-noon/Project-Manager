package ch.wiss.m223.project_manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import ch.wiss.m223.project_manager.model.Project;
import ch.wiss.m223.project_manager.model.User;
import ch.wiss.m223.project_manager.repositories.ProjectRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private ProjectService projectService;

  @Test
  public void testSaveProject_success() {
    // Arrange
    User owner = new User();
    owner.setId(1L);
    owner.setUsername("testuser");

    Project project = new Project();
    project.setName("Testprojekt");
    project.setDescription("Beschreibung");
    project.setOwner(owner);

    when(projectRepository.save(any(Project.class))).thenReturn(project);

    // Act
    Project saved = projectService.save(project);

    // Assert
    assertNotNull(saved);
    assertEquals("Testprojekt", saved.getName());
    assertEquals("Beschreibung", saved.getDescription());
    assertEquals("testuser", saved.getOwner().getUsername());
  }
}
