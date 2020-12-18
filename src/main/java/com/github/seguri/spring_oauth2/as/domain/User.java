package com.github.seguri.spring_oauth2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.PersistenceConstructor;

/** Defines a User as a JPA Entity. */
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull private String password;
  @NotNull private String authority;

  @PersistenceConstructor
  public User() {}

  public User(String username, String password, String authority) {
    this.username = username;
    this.password = password;
    this.authority = authority;
  }

  public static User from(User other, String password) {
    User user = new User();
    user.username = other.username;
    user.password = password;
    user.authority = other.authority;
    return user;
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

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", authority='"
        + authority
        + '\''
        + '}';
  }
}
