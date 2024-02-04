package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Test;
import com.codeless.api.automation.exception.EntityAlreadyExistsException;
import com.codeless.api.automation.exception.PersistenceException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

public class TestRepository {

  private final DynamoDbTable<Test> testTable;

  public TestRepository(DynamoDbEnhancedClient dynamo) {
    this.testTable = dynamo.table(Test.TABLE_NAME, TableSchema.fromBean(Test.class));
  }

  public void create(Test test) {
    Objects.requireNonNull(test);
    Map<String, String> expressionNames = new HashMap<>();
    expressionNames.put("#u", Test.PARTITION_KEY_ATTRIBUTE_NAME);

    Expression entityDoesNotExist = Expression.builder()
        .expression("attribute_not_exists(#u)")
        .expressionNames(expressionNames)
        .build();
    PutItemEnhancedRequest<Test> request = PutItemEnhancedRequest.builder(Test.class)
        .item(test)
        .conditionExpression(entityDoesNotExist)
        .build();
    try {
      testTable.putItem(request);
    } catch (ConditionalCheckFailedException e) {
      throw new EntityAlreadyExistsException(String.format(
          "Test already exists %s", test.getId()), e);
    }
  }

  public void put(Test test) {
    Objects.requireNonNull(test);
    testTable.putItem(test);
  }

  public Test get(String id) {
    Objects.requireNonNull(id);
    return testTable.getItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public void delete(String id) {
    Objects.requireNonNull(id);
    this.testTable.deleteItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public Page<Test> listTestsByCustomerId(
      String customerId,
      Map<String, AttributeValue> lastEvaluatedKey,
      Integer maxResults) {
    try {
      QueryEnhancedRequest request = QueryEnhancedRequest.builder()
          .queryConditional(QueryConditional.keyEqualTo(Key.builder()
              .partitionValue(customerId)
              .build()))
          .limit(maxResults)
          .exclusiveStartKey(lastEvaluatedKey)
          .scanIndexForward(false) // set to false for descending order by created date
          .build();
      return testTable.index(Test.GSI_TESTS_BY_CUSTOMER_ID)
          .query(request).stream()
          .limit(1)
          .collect(Collectors.toList())
          .get(0);
    } catch (SdkException e) {
      throw new PersistenceException(e);
    }
  }

}
