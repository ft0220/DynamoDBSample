package com.sample.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@Component
@EnableDynamoDBRepositories(basePackages = "com.sample.dynamodb.model")
public class DynamoDBConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String endpointurl;

  @Value("${amazon.dynamodb.region}")
  private String region;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {

    AmazonDynamoDB client =
        AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(endpointurl, region))
            .build();

    return client;
  }

  @Bean
  public DynamoDBMapper getDynamoDBMapper() {
    return new DynamoDBMapper(amazonDynamoDB());
  }
}
