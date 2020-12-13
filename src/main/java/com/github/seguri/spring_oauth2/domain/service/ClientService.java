package com.github.seguri.spring_oauth2.domain.service;

import com.github.seguri.spring_oauth2.domain.Client;
import com.github.seguri.spring_oauth2.domain.repository.ClientRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
  private final ClientRepository clientRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Optional<Client> findByClientId(String clientId) {
    return clientRepository.findByClientId(clientId);
  }

  public Client save(Client client) {
    return clientRepository.save(client);
  }

  public void saveTestClient() {
    Client client = new Client("client", "secret", "read");
    Client saved = save(client);
    LOGGER.info("Saved '{}'", saved);
  }
}
