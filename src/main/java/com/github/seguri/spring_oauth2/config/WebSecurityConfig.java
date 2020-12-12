package com.github.seguri.spring_oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/** User management. */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    var uds = new InMemoryUserDetailsManager();
    var u = User.withUsername("john").password("12345").authorities("read").build();
    uds.createUser(u);
    return uds;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
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

  /**
   * Go to http://localhost:8080/oauth/authorize?response_type=code&client_id=client&scope=read to
   * be redirected to the login form at http://localhost:8080/login
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin();
  }
}
