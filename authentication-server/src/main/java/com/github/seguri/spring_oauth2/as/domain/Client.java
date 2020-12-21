package com.github.seguri.spring_oauth2.as.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true)
  private String clientId;

  @NotNull private String clientSecret;
  @NotNull private String scope;

  @PersistenceConstructor
  public Client() {}

  public Client(String clientId, String clientSecret, String scope) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scope = scope;
  }

  public static Client from(Client other, String clientSecret) {
    Client client = new Client();
    client.clientId = other.clientId;
    client.clientSecret = clientSecret;
    client.scope = other.scope;
    return client;
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
