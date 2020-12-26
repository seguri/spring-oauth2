package com.github.seguri.spring_oauth2.rs.exceptions;

public class HealthProfileAlreadyExistsException extends RuntimeException {

  public HealthProfileAlreadyExistsException() {
    super("This health profile already exists.");
  }

  public HealthProfileAlreadyExistsException(String message) {
    super(message);
  }

  public static void throwDefault() {
    throw new HealthProfileAlreadyExistsException();
  }
}
