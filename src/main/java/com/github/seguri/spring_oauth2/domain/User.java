package com.github.seguri.spring_oauth2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.annotation.PersistenceConstructor;

/** Defines a User as a JPA Entity. */
@Entity
public class User {

  @Id private Long id;
  private String username;
  private String password;
  private String authority;

  @PersistenceConstructor
  public User() {}

  public User(String username, String password, String authority) {
    this.username = username;
    this.password = password;
    this.authority = authority;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAuthority() {
    return authority;
  }
}
