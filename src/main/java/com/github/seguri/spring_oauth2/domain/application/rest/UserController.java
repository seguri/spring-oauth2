package com.github.seguri.spring_oauth2.domain.application.rest;

import com.github.seguri.spring_oauth2.domain.User;
import com.github.seguri.spring_oauth2.domain.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = "application/json")
public class UserController {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping
  public List<User> getUsers() {
    return userService.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody User user) {
    User userWithEncryptedPassword = User.from(user, passwordEncoder.encode(user.getPassword()));
    User saved = userService.save(userWithEncryptedPassword);
    LOGGER.info("Saved '{}'", saved);
    return saved;
  }
}
