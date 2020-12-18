package com.github.seguri.spring_oauth2.as.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** User management. */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Link the User Management to the Authorization Server by exposing the AuthenticationManager in
   * the Spring context as a bean.
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().ignoringAntMatchers("/h2-console/**", "/users/**", "/clients/**");
    http.authorizeRequests().mvcMatchers("/h2-console/**", "/users/**", "/clients/**").permitAll();
    http.headers().frameOptions().sameOrigin();
    super.configure(http);
  }
}
