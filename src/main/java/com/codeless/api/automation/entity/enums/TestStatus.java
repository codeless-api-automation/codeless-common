package com.codeless.api.automation.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestStatus {
  SUCCESS("SUCCESS"),
  FAIL("FAIL");

  private final String name;
}
