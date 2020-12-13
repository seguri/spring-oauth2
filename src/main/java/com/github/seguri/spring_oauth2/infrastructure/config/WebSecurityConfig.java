package com.github.seguri.spring_oauth2.infrastructure.config;

import com.github.seguri.spring_oauth2.domain.SimpleSecurityUser;
import com.github.seguri.spring_oauth2.domain.service.InMemoryUserDetailsService;
import com.github.seguri.spring_oauth2.domain.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** User management. */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private UserService userService;

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    List<UserDetails> usersDetails =
        userService.findAll().stream().map(SimpleSecurityUser::new).collect(Collectors.toList());
    return new InMemoryUserDetailsService(usersDetails);
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

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().ignoringAntMatchers("/h2-console/**", "/users/**", "/clients/**");
    http.authorizeRequests().mvcMatchers("/h2-console/**", "/users/**", "/clients/**").permitAll();
    http.headers().frameOptions().sameOrigin();
    super.configure(http);
  }
}
