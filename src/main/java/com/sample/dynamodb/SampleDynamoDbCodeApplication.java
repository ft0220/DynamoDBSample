package com.sample.dynamodb;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

@SpringBootApplication
public class SampleDynamoDbCodeApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SampleDynamoDbCodeApplication.class, args);
  }

  @Autowired DynamoDBConfig config;

  @Override
  public void run(String... args) throws Exception {

    // this is create table
    List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
    attributeDefinitions.add(
        new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));

    List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
    keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));

    CreateTableRequest request =
        new CreateTableRequest()
            .withTableName("Test")
            .withKeySchema(keySchema)
            .withAttributeDefinitions(attributeDefinitions)
            .withProvisionedThroughput(
                new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(6L));

    config.amazonDynamoDB().createTable(request);
  }
}
