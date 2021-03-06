package com.github.seguri.spring_oauth2.as.domain.service;

import com.github.seguri.spring_oauth2.as.domain.Client;
import com.github.seguri.spring_oauth2.as.domain.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  private final ClientRepository clientRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  public Optional<Client> findByClientId(String clientId) {
    return clientRepository.findByClientId(clientId);
  }

  public Client save(Client client) {
    return clientRepository.save(client);
  }
}
