package com.codeless.api.automation.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExecutionStatus {
  PENDING("PENDING"),
  STARTED("STARTED"),
  COMPLETED("COMPLETED");

  private final String name;
}
