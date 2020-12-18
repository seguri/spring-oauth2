package com.github.seguri.spring_oauth2.as.domain.service;

import com.github.seguri.spring_oauth2.as.domain.SimpleOauth2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class JpaClientDetailsService implements ClientDetailsService {

  @Autowired private ClientService clientService;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    return clientService
        .findByClientId(clientId)
        .map(SimpleOauth2Client::new)
        .orElseThrow(() -> new ClientRegistrationException(clientId + ": no such clientId"));
  }
}
