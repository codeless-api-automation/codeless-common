package com.codeless.api.automation.entity;

import java.time.Instant;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

public abstract class BaseEntity {

  private Instant created;
  private Instant lastModified;

  @DynamoDbAttribute(value = "lastModified")
  public Instant getLastModified() {
    return lastModified;
  }

  public void setLastModified(Instant lastModified) {
    this.lastModified = lastModified;
  }

  @DynamoDbAttribute(value = "created")
  public Instant getCreated() {
    return created;
  }

  public void setCreated(Instant created) {
    this.created = created;
  }

}
