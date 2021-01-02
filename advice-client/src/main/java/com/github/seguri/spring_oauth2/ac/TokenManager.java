package com.github.seguri.spring_oauth2.ac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

  private final OAuth2AuthorizedClientManager authorizedClientManager;

  @Value("${client.registration.name}")
  private String clientRegistrationName;

  @Value("${spring.security.oauth2.client.registration.app.client-id}")
  private String clientId;

  public TokenManager(OAuth2AuthorizedClientManager authorizedClientManager) {
    this.authorizedClientManager = authorizedClientManager;
  }

  public String getAccessToken() {
    var authorizeRequest =
        OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationName)
            .principal(clientId)
            .build();

    var authorizedClient = authorizedClientManager.authorize(authorizeRequest);

    return authorizedClient.getAccessToken().getTokenValue();
  }
}
