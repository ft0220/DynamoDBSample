package com.sample.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Setter;

@Setter
@DynamoDBTable(tableName = "Test")
public class Data {

  private String id;
  private String name;

  @DynamoDBHashKey(attributeName = "Id")
  public String getId() {
    return id;
  }

  @DynamoDBAttribute(attributeName = "Name")
  public String getName() {
    return name;
  }
}
