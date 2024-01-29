package com.codeless.api.automation.entity;

import com.codeless.api.automation.entity.enums.ExecutionStatus;
import com.codeless.api.automation.entity.enums.ExecutionType;
import com.codeless.api.automation.entity.enums.TestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
@DynamoDbImmutable(builder = Execution.ExecutionBuilder.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Execution extends BaseEntity {

  public static final String TABLE_NAME = "execution";
  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "id";
  public static final String GIS_EXECUTIONS_BY_TEST_ID = "GIS_EXECUTIONS_BY_TEST_ID";
  public static final String GIS_EXECUTIONS_BY_SCHEDULE_ID = "GIS_EXECUTIONS_BY_SCHEDULE_ID";
  public static final String GIS_EXECUTIONS_BY_CUSTOMER_ID = "GIS_EXECUTIONS_BY_CUSTOMER_ID";

  private String id;
  private String name;
  private ExecutionType type;
  private ExecutionStatus executionStatus;
  private String scheduleId;
  private String regionName;
  private String testId;
  private TestStatus testStatus;
  private String logs;
  private String customerId;

  @DynamoDbPartitionKey
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDbAttribute(value = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @DynamoDbAttribute(value = "type")
  public ExecutionType getType() {
    return type;
  }

  public void setType(ExecutionType type) {
    this.type = type;
  }

  @DynamoDbAttribute(value = "executionStatus")
  public ExecutionStatus getExecutionStatus() {
    return executionStatus;
  }

  public void setExecutionStatus(ExecutionStatus executionStatus) {
    this.executionStatus = executionStatus;
  }

  @DynamoDbAttribute(value = "scheduleId")
  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_EXECUTIONS_BY_SCHEDULE_ID})
  public String getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
  }

  @DynamoDbAttribute(value = "regionName")
  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  @DynamoDbAttribute(value = "testId")
  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_EXECUTIONS_BY_TEST_ID})
  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  @DynamoDbAttribute(value = "testStatus")
  public TestStatus getTestStatus() {
    return testStatus;
  }

  public void setTestStatus(TestStatus testStatus) {
    this.testStatus = testStatus;
  }

  @DynamoDbAttribute(value = "logs")
  public String getLogs() {
    return logs;
  }

  public void setLogs(String logs) {
    this.logs = logs;
  }

  @DynamoDbAttribute(value = "customerId")
  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_EXECUTIONS_BY_CUSTOMER_ID})
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

}
