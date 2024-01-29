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
@DynamoDbImmutable(builder = Test.TestBuilder.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test extends BaseEntity {

  public static final String TABLE_NAME = "test";
  public static final String GIS_TESTS_BY_CUSTOMER_ID = "GIS_TESTS_BY_CUSTOMER_ID";
  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "id";

  private String id;
  private String name;
  private String json;
  private String customerId;

  @DynamoDbPartitionKey
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDbAttribute(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @DynamoDbAttribute(value = "json")
  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  @DynamoDbAttribute(value = "customerId")
  @DynamoDbSecondaryPartitionKey(indexNames = {GIS_TESTS_BY_CUSTOMER_ID})
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
