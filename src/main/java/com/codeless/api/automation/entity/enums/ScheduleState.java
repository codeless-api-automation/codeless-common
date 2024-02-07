package com.codeless.api.automation.entity.enums;

public enum ScheduleState {
  ENABLED("ENABLED"),
  DISABLED("DISABLED");

  private final String value;

  ScheduleState(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}