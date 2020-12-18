package com.github.seguri.spring_oauth2.as.application.rest;

import com.github.seguri.spring_oauth2.as.domain.Client;
import com.github.seguri.spring_oauth2.as.domain.service.ClientService;
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
@RequestMapping(path = "/clients", produces = "application/json")
public class ClientController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

  @Autowired private ClientService clientService;
  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping
  public List<Client> getClients() {
    return clientService.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Client createClient(@RequestBody Client client) {
    Client clientWithEncryptedClientSecret =
        Client.from(client, passwordEncoder.encode(client.getClientSecret()));
    Client saved = clientService.save(clientWithEncryptedClientSecret);
    LOGGER.info("Saved '{}'", saved);
    return saved;
  }
}
