package com.codeless.api.automation.exception;

public class EntityAlreadyExistsException extends RuntimeException {

  public EntityAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
