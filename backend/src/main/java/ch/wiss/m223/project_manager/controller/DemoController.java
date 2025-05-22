package ch.wiss.m223.project_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

  @GetMapping("/public")
  public ResponseEntity<String> publicEndpoint() {
    return ResponseEntity.ok("This is a public endpoint.");
  }

  @GetMapping("/private")
  public ResponseEntity<String> privateEndpoint() {
    return ResponseEntity.ok("This is a private endpoint.");
  }
  
}
