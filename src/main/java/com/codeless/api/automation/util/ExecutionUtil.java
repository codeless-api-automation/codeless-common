package com.codeless.api.automation.util;

import java.time.Duration;

public final class ExecutionUtil {

  public static final Duration DEFAULT_EXECUTION_EXPIRATION_TIME = Duration.ofDays(5);

  public static long getExecutionExpirationTime(Duration duration) {
    return (System.currentTimeMillis() / 1000L) + duration.toSeconds();
  }

  public static  long getDefaultExecutionExpirationTime() {
    return getExecutionExpirationTime(DEFAULT_EXECUTION_EXPIRATION_TIME);
  }
}
