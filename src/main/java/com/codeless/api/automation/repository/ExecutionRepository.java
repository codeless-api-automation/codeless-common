package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Execution;
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

public class ExecutionRepository {

  private final DynamoDbTable<Execution> executionTable;

  public ExecutionRepository(DynamoDbEnhancedClient dynamo) {
    this.executionTable = dynamo.table(Execution.TABLE_NAME,
        TableSchema.fromBean(Execution.class));
  }

  public void create(Execution execution) {
    Objects.requireNonNull(execution);
    Map<String, String> expressionNames = new HashMap<>();
    expressionNames.put("#u", Execution.PARTITION_KEY_ATTRIBUTE_NAME);

    Expression entityDoesNotExist = Expression.builder()
        .expression("attribute_not_exists(#u)")
        .expressionNames(expressionNames)
        .build();
    PutItemEnhancedRequest<Execution> request = PutItemEnhancedRequest.builder(Execution.class)
        .item(execution)
        .conditionExpression(entityDoesNotExist)
        .build();
    try {
      executionTable.putItem(request);
    } catch (ConditionalCheckFailedException e) {
      throw new EntityAlreadyExistsException(String.format(
          "Execution already exists %s", execution.getId()), e);
    }
  }

  public void put(Execution execution) {
    executionTable.putItem(execution);
  }

  public Execution get(String id) {
    return executionTable.getItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public void delete(String id) {
    executionTable.deleteItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public Page<Execution> listExecutionsByCustomerId(
      String customerId,
      Map<String, AttributeValue> lastEvaluatedKey,
      Integer maxResults) {
    //TODO: project fields which are necessary
    try {
      QueryEnhancedRequest request = QueryEnhancedRequest.builder()
          .queryConditional(QueryConditional.keyEqualTo(Key.builder()
              .partitionValue(customerId)
              .build()))
          .limit(maxResults)
          .exclusiveStartKey(lastEvaluatedKey)
          .scanIndexForward(false) // set to false for descending order by created date
          .build();
      return executionTable.index(Execution.GSI_EXECUTIONS_BY_CUSTOMER_ID)
          .query(request).stream()
          .limit(1)
          .collect(Collectors.toList())
          .get(0);
    } catch (SdkException e) {
      throw new PersistenceException(e);
    }
  }

  public Page<Execution> listExecutionsByScheduleId(
      String scheduleId,
      Map<String, AttributeValue> lastEvaluatedKey,
      Integer maxResults) {
    try {
      QueryEnhancedRequest request = QueryEnhancedRequest.builder()
          .queryConditional(QueryConditional.keyEqualTo(Key.builder()
              .partitionValue(scheduleId)
              .build()))
          .limit(maxResults)
          .exclusiveStartKey(lastEvaluatedKey)
          .scanIndexForward(false) // set to false for descending order by created date
          .build();
      return executionTable.index(Execution.GSI_EXECUTIONS_BY_SCHEDULE_ID)
          .query(request).stream()
          .limit(1)
          .collect(Collectors.toList())
          .get(0);
    } catch (SdkException e) {
      throw new PersistenceException(e);
    }
  }
}
