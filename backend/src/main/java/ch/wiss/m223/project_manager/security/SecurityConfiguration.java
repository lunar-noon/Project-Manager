package ch.wiss.m223.project_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {
  @Bean
  public UserDetailsService users(@Autowired PasswordEncoder pwEnc){
    UserDetails user = User.builder()
    .username("user")
    .password(pwEnc.encode("top"))
    .roles("USER")
    .build();
    UserDetails admin = User.builder()
    .username("admin")
    .password(pwEnc.encode("secret"))
    .roles("USER", "ADMIN")
    .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
