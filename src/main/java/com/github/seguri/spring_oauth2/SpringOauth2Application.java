package com.github.seguri.spring_oauth2;

import com.github.seguri.spring_oauth2.domain.service.ClientService;
import com.github.seguri.spring_oauth2.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringOauth2Application implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(SpringOauth2Application.class);

  @Autowired UserService userService;
  @Autowired ClientService clientService;

  public static void main(String[] args) {
    SpringApplication.run(SpringOauth2Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    userService.findByUsername("john").ifPresentOrElse(user -> {}, userService::saveTestUser);
    clientService
        .findByClientId("client")
        .ifPresentOrElse(client -> {}, clientService::saveTestClient);
  }
}
