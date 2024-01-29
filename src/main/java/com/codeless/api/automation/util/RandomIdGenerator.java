package com.codeless.api.automation.util;

import java.util.UUID;

public final class RandomIdGenerator {

  public static String generateTestId() {
    return String.format("tid-%s", UUID.randomUUID());
  }

  public static String generateScheduleId() {
    return String.format("sid-%s", UUID.randomUUID());
  }

  public static String generateExecutionId() {
    return String.format("eid-%s", UUID.randomUUID());
  }
}
