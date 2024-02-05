package com.codeless.api.automation.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExecutionType {
  MANUAL_EXECUTION("MANUAL_EXECUTION"),
  SCHEDULED_EXECUTION("SCHEDULED_EXECUTION");

  private final String name;
}
