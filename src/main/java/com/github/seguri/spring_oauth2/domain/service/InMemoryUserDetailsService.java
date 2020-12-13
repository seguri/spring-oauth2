package com.github.seguri.spring_oauth2.domain.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** You must restart the application when you add a new user. */
public class InMemoryUserDetailsService implements UserDetailsService {

  private final List<UserDetails> usersDetails;

  public InMemoryUserDetailsService(List<UserDetails> usersDetails) {
    this.usersDetails = usersDetails;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usersDetails.stream()
        .filter(ud -> username.equals(ud.getUsername()))
        .findFirst()
        .orElseThrow(() -> new UsernameNotFoundException(username + ": no such user"));
  }
}
