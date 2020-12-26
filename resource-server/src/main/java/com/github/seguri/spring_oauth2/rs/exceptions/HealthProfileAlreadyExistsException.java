package com.github.seguri.spring_oauth2.rs.exceptions;

import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;

public class HealthProfileAlreadyExistsException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This health profile already exists.";

  public HealthProfileAlreadyExistsException() {
    super(DEFAULT_MESSAGE);
  }

  public HealthProfileAlreadyExistsException(String message) {
    super(message);
  }

  public static void throwDefault() {
    throw new HealthProfileAlreadyExistsException();
  }

  public static void throwWith(HealthProfile profile) {
    throw new HealthProfileAlreadyExistsException(
        String.format("%s: %s", profile.getUsername(), DEFAULT_MESSAGE));
  }
}
