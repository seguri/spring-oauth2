package com.github.seguri.spring_oauth2.as;

import com.github.seguri.spring_oauth2.as.domain.service.ClientService;
import com.github.seguri.spring_oauth2.as.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationServerApplication implements CommandLineRunner {
  private final UserService userService;
  private final ClientService clientService;

  public AuthenticationServerApplication(UserService userService, ClientService clientService) {
    this.userService = userService;
    this.clientService = clientService;
  }

  public static void main(String[] args) {
    SpringApplication.run(AuthenticationServerApplication.class, args);
  }

  @Override
  public void run(String... args) {
    userService.findByUsername("john").ifPresentOrElse(user -> {}, userService::saveTestUser);
    clientService
        .findByClientId("client")
        .ifPresentOrElse(client -> {}, clientService::saveTestClient);
  }
}