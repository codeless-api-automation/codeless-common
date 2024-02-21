package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Schedule;
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

public class ScheduleRepository {

  private final DynamoDbTable<Schedule> scheduleTable;

  public ScheduleRepository(DynamoDbEnhancedClient dynamo) {
    this.scheduleTable = dynamo.table(Schedule.TABLE_NAME, TableSchema.fromBean(Schedule.class));
  }

  public void create(Schedule schedule) {
    Objects.requireNonNull(schedule);
    Map<String, String> expressionNames = new HashMap<>();
    expressionNames.put("#u", Schedule.PARTITION_KEY_ATTRIBUTE_NAME);

    Expression entityDoesNotExist = Expression.builder()
        .expression("attribute_not_exists(#u)")
        .expressionNames(expressionNames)
        .build();
    PutItemEnhancedRequest<Schedule> request = PutItemEnhancedRequest.builder(Schedule.class)
        .item(schedule)
        .conditionExpression(entityDoesNotExist)
        .build();
    try {
      scheduleTable.putItem(request);
    } catch (ConditionalCheckFailedException e) {
      throw new EntityAlreadyExistsException(String.format(
          "Schedule already exists %s", schedule.getId()), e);
    }
  }

  public void put(Schedule schedule) {
    Objects.requireNonNull(schedule);
    scheduleTable.putItem(schedule);
  }

  public Schedule get(String id) {
    return scheduleTable.getItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public void delete(String id) {
    this.scheduleTable.deleteItem(Key.builder()
        .partitionValue(id)
        .build());
  }

  public Page<Schedule> listSchedulesByTestId(
      String testId,
      Map<String, AttributeValue> lastEvaluatedKey,
      Integer maxResults) {
    try {
      QueryEnhancedRequest request = QueryEnhancedRequest.builder()
          .queryConditional(QueryConditional.keyEqualTo(Key.builder()
              .partitionValue(testId)
              .build()))
          .limit(maxResults)
          .exclusiveStartKey(lastEvaluatedKey)
          .build();
      return scheduleTable.index(Schedule.GSI_SCHEDULES_BY_TEST_ID)
          .query(request).stream()
          .limit(1)
          .collect(Collectors.toList())
          .get(0);
    } catch (SdkException e) {
      throw new PersistenceException(e);
    }
  }

  public Page<Schedule> listSchedulesByCustomerId(
      String customerId,
      Map<String, AttributeValue> lastEvaluatedKey,
      Integer maxResults
  ) {
    try {
      QueryEnhancedRequest request = QueryEnhancedRequest.builder()
          .queryConditional(QueryConditional.keyEqualTo(Key.builder()
              .partitionValue(customerId)
              .build()))
          .limit(maxResults)
          .exclusiveStartKey(lastEvaluatedKey)
          .scanIndexForward(false) // set to false for descending order by created date
          .build();
      return scheduleTable.index(Schedule.GSI_SCHEDULES_BY_CUSTOMER_ID)
          .query(request).stream()
          .limit(1)
          .collect(Collectors.toList())
          .get(0);
    } catch (SdkException e) {
      throw new PersistenceException(e);
    }
  }


}
