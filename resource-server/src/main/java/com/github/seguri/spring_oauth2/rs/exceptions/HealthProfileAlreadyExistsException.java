package com.github.seguri.spring_oauth2.rs.exceptions;

public class HealthProfileAlreadyExistsException extends RuntimeException {

  public HealthProfileAlreadyExistsException(String message) {
    super(message);
  }
}
