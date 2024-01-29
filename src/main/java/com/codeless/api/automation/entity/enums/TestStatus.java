package com.codeless.api.automation.entity.enums;

public enum TestStatus {
  SUCCESS("SUCCESS"),
  FAIL("FAIL");

  private final String name;

  TestStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
