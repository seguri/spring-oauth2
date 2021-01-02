package com.github.seguri.spring_oauth2.ac;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResourceServerClient {

  private final TokenManager tokenManager;
  private final RestTemplate restTemplate;

  @Value("${resourceServerUri}")
  private String resourceServerUri;

  public ResourceServerClient(TokenManager tokenManager, RestTemplate restTemplate) {
    this.tokenManager = tokenManager;
    this.restTemplate = restTemplate;
  }

  public ResponseEntity<Void> postAdvice(List<HealthAdvice> healthAdvices) {
    var uri = resourceServerUri + "/advice";
    var request = new HttpEntity<>(healthAdvices, headers());
    return restTemplate.postForEntity(uri, request, Void.class);
  }

  private HttpHeaders headers() {
    var headers = new HttpHeaders();
    headers.add(AUTHORIZATION, "Bearer " + tokenManager.getAccessToken());
    return headers;
  }
}
