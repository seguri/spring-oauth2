package com.github.seguri.spring_oauth2.domain.application.rest;

import com.github.seguri.spring_oauth2.domain.Client;
import com.github.seguri.spring_oauth2.domain.service.ClientService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  @GetMapping
  public List<Client> getClients() {
    return clientService.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Client createClient(@RequestBody Client client) {
    Client saved = clientService.save(client);
    LOGGER.info("Saved '{}'", saved);
    return saved;
  }
}
