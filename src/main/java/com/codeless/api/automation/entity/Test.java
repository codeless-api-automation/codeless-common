package com.codeless.api.automation.entity;

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
public class Test {

  public static final String TABLE_NAME = "test";
  public static final String GSI_TESTS_BY_CUSTOMER_ID = "GSI_TESTS_BY_CUSTOMER_ID";
  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "id";

  @Setter
  @Getter(onMethod = @__({@DynamoDbPartitionKey}))
  private String id;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "name")}))
  private String name;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "json")}))
  private String json;
  @Setter
  @Getter(onMethod = @__({
      @DynamoDbAttribute(value = "customerId"),
      @DynamoDbSecondaryPartitionKey(indexNames = {GSI_TESTS_BY_CUSTOMER_ID})}))
  private String customerId;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "created")}))
  private Instant created;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "lastModified")}))
  private Instant lastModified;

}
