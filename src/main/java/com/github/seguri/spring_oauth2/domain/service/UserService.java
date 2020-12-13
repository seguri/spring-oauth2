package com.github.seguri.spring_oauth2.domain.service;

import com.github.seguri.spring_oauth2.domain.User;
import com.github.seguri.spring_oauth2.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void saveTestUser() {
    User client = new User("john", "12345", "read");
    User saved = save(client);
    LOGGER.info("Saved '{}'", saved);
  }
}
