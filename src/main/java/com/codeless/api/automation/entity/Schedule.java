package com.codeless.api.automation.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
@DynamoDbImmutable(builder = Schedule.ScheduleBuilder.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule extends BaseEntity {

  public static final String TABLE_NAME = "schedule";

  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "id";
  public static final String GIS_SCHEDULES_BY_CUSTOMER_ID = "GIS_SCHEDULES_BY_CUSTOMER_ID";
  public static final String GIS_SCHEDULES_BY_TEST_ID = "GIS_SCHEDULES_BY_TEST_ID";

  private String id;
  private String name;
  private String timer;
  private String emails;
  private String testId;
  private String regionName;
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

  @DynamoDbAttribute(value = "timer")
  public String getTimer() {
    return timer;
  }

  public void setTimer(String timer) {
    this.timer = timer;
  }

  @DynamoDbAttribute(value = "emails")
  public String getEmails() {
    return emails;
  }

  public void setEmails(String emails) {
    this.emails = emails;
  }

  @DynamoDbAttribute(value = "testId")
  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_SCHEDULES_BY_TEST_ID})
  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  @DynamoDbAttribute(value = "regionName")
  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_SCHEDULES_BY_CUSTOMER_ID})
  @DynamoDbAttribute(value = "customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
