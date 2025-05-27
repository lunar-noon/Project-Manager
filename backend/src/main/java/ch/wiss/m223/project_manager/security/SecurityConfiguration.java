package ch.wiss.m223.project_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
  
  @Autowired private UserDetailsServiceImpl userDetailsService;
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private static final String[] EVERYONE = { "/api/auth/**", "/public"};

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable()) //disable Cross-Site Request Forgery (CSRF) prevention
    .cors(Customizer.withDefaults()) //configure CORS: Cross Origin Request Sharing
    .authorizeHttpRequests(auth -> {
      //auth.requestMatchers(HttpMethod.POST, "/items").hasRole("ADMIN");
      auth.requestMatchers(EVERYONE).permitAll()
      .anyRequest().authenticated(); } )
      .formLogin(Customizer.withDefaults()) //für Login-Form im Browser
      .httpBasic(Customizer.withDefaults()); // für CURL, Postman, Insomnia
    return http.build();
  }

}
