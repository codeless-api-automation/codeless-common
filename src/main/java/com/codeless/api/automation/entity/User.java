package com.codeless.api.automation.entity;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  public static final String TABLE_NAME = "user";
  public static final String PARTITION_KEY_ATTRIBUTE_NAME = "username";

  @Setter
  @Getter(onMethod = @__({@DynamoDbPartitionKey}))
  private String username;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "firstName")}))
  private String firstName;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "lastName")}))
  private String lastName;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "password")}))
  private String password;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "isAccountNonExpired")}))
  private boolean isAccountNonExpired;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "isAccountNonLocked")}))
  private boolean isAccountNonLocked;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "isCredentialsNonExpired")}))
  private boolean isCredentialsNonExpired;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "isEnabled")}))
  private boolean isEnabled;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "token")}))
  private String token;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "created")}))
  private Instant created;
  @Setter
  @Getter(onMethod = @__({@DynamoDbAttribute(value = "lastModified")}))
  private Instant lastModified;
}
