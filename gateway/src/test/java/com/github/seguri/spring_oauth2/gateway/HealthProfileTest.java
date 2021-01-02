package com.github.seguri.spring_oauth2.gateway;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@ContextConfiguration(initializers = {WireMockContextInitializer.class})
public class HealthProfileTest {

  @Autowired private WebTestClient client;

  @Autowired private WireMockServer wireMockServer;

  @Value("${resourceServerUri}")
  private String resourceServerUri;

  @AfterEach
  public void afterEach() {
    wireMockServer.resetAll();
  }

  @Test
  public void getProfileUnauthorizedTest() {
    client
        .get()
        .uri(resourceServerUri + "/profile/john")
        .exchange()
        .expectStatus()
        .isUnauthorized();
  }

  @Test
  public void getProfileAuthorizedTest() {
    client
        .mutateWith(mockJwt())
        .get()
        .uri(resourceServerUri + "/profile/john")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(2)
        .jsonPath("$.username")
        .isEqualTo("john");
  }
}
