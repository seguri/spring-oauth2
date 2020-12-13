package com.github.seguri.spring_oauth2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String clientId;
  private String clientSecret;
  private String scope;

  @PersistenceConstructor
  public Client() {}

  public Client(String clientId, String clientSecret, String scope) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scope = scope;
  }

  public Long getId() {
    return id;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getScope() {
    return scope;
  }

  @Override
  public String toString() {
    return "Client{"
        + "id="
        + id
        + ", clientId='"
        + clientId
        + '\''
        + ", clientSecret='"
        + clientSecret
        + '\''
        + ", scope='"
        + scope
        + '\''
        + '}';
  }
}
