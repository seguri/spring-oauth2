package com.github.seguri.spring_oauth2.rs.exceptions;

public class NonExistentHealthProfileException extends RuntimeException {

  public NonExistentHealthProfileException(String message) {
    super(message);
  }
}
