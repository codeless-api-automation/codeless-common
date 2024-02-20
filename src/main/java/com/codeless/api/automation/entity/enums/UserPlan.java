package com.codeless.api.automation.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserPlan {
  FREE("FREE"),
  BASIC("BASIC"),
  PRO("PRO");

  private final String value;
}
