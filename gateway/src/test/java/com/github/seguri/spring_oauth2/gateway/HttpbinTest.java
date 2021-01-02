package com.github.seguri.spring_oauth2.gateway;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class HttpbinTest {

  @Autowired private WebTestClient client;

  @Test
  public void anythingUnauthorizedTest() {
    client.get().uri("/anything").exchange().expectStatus().isUnauthorized();
  }

  @Test
  public void anythingAuthorizedTest() {
    client
        .mutateWith(mockJwt())
        .get()
        .uri("/anything?bar=baz")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.args.bar")
        .isEqualTo("baz");
  }
}
