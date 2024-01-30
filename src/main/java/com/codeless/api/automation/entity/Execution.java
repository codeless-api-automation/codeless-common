package com.codeless.api.automation.entity;

import com.codeless.api.automation.entity.enums.ExecutionStatus;
import com.codeless.api.automation.entity.enums.ExecutionType;
import com.codeless.api.automation.entity.enums.TestStatus;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class Execution {

  public static final String TABLE_NAME = "execution";
  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "id";
  public static final String GIS_EXECUTIONS_BY_SCHEDULE_ID = "GIS_EXECUTIONS_BY_SCHEDULE_ID";
  public static final String GIS_EXECUTIONS_BY_CUSTOMER_ID = "GIS_EXECUTIONS_BY_CUSTOMER_ID";

  @Setter
  @Getter(onMethod = @__({@DynamoDbPartitionKey}))
  private String id;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "name")}))
  private String name;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "type")}))
  private ExecutionType type;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "executionStatus")}))
  private ExecutionStatus executionStatus;
  @Setter
  @Getter(onMethod = @__({
      @DynamoDbAttribute(value = "scheduleId"),
      @DynamoDbSecondaryPartitionKey(indexNames = {GIS_EXECUTIONS_BY_SCHEDULE_ID})}))
  private String scheduleId;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "regionName")}))
  private String regionName;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "testId")}))
  private String testId;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "testStatus")}))
  private TestStatus testStatus;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "logs")}))
  private String logs;
  @Setter
  @Getter(onMethod = @__({
      @DynamoDbAttribute(value = "customerId"),
      @DynamoDbSecondaryPartitionKey(indexNames = {GIS_EXECUTIONS_BY_CUSTOMER_ID})}))
  private String customerId;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "created")}))
  private Instant created;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "lastModified")}))
  private Instant lastModified;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "ttl")}))
  private Long ttl;
}
