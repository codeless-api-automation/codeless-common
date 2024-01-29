package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.User;
import com.codeless.api.automation.exception.EntityAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

public class UserRepository {

  private final DynamoDbTable<User> userTable;

  public UserRepository(DynamoDbEnhancedClient dynamo) {
    this.userTable = dynamo.table(User.TABLE_NAME, TableSchema.fromBean(User.class));
  }

  public User get(String username) {
    Objects.requireNonNull(username);
    return userTable.getItem(Key.builder()
        .partitionValue(username)
        .build());
  }

  public void put(User user) {
    userTable.putItem(user);
  }

  public void create(User user) {
    Objects.requireNonNull(user);
    Map<String, String> expressionNames = new HashMap<>();
    expressionNames.put("#u", User.PARTITION_KEY_ATTRIBUTE_NAME);

    Expression entityDoesNotExist = Expression.builder()
        .expression("attribute_not_exists(#u)")
        .expressionNames(expressionNames)
        .build();
    PutItemEnhancedRequest<User> request = PutItemEnhancedRequest.builder(User.class)
        .item(user)
        .conditionExpression(entityDoesNotExist)
        .build();
    try {
      userTable.putItem(request);
    } catch (ConditionalCheckFailedException e) {
      throw new EntityAlreadyExistsException(String.format(
          "User already exists %s", user.getUsername()), e);
    }
  }
}
