package com.github.seguri.spring_oauth2.as.domain.service;

import com.github.seguri.spring_oauth2.as.domain.SimpleSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** This is the implementation of UserDetailsService that Spring will load into its context. */
@Service
public class JpaUserDetailsService implements UserDetailsService {

  @Autowired private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userService
        .findByUsername(username)
        .map(SimpleSecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException(username + ": no such user"));
  }
}
