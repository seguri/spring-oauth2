package com.github.seguri.spring_oauth2.rs.exceptions;

public class NonExistentHealthProfileException extends RuntimeException {

  public NonExistentHealthProfileException() {
    super("No profile found for the provided username.");
  }

  public NonExistentHealthProfileException(String message) {
    super(message);
  }

  public static void throwDefault() {
    throw new NonExistentHealthProfileException();
  }
}
