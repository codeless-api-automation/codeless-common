package com.codeless.api.automation.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN");

  private final String value;
}
