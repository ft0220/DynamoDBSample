package com.sample.dynamodb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputDescription;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.sample.dynamodb.DynamoDBConfig;
import com.sample.dynamodb.model.Data;

@Component
public class DataServiceImpl implements DataService {

  @Autowired DynamoDBConfig aWSConfiguration;

  @Override
  public void getDescribe() {
    TableDescription table_info =
        aWSConfiguration.amazonDynamoDB().describeTable("Test").getTable();
    if (table_info != null) {
      System.out.format("Table name  : %s\n", table_info.getTableName());
      System.out.format("Table ARN   : %s\n", table_info.getTableArn());
      System.out.format("Status      : %s\n", table_info.getTableStatus());
      System.out.format("Item count  : %d\n", table_info.getItemCount().longValue());
      System.out.format("Size (bytes): %d\n", table_info.getTableSizeBytes().longValue());

      ProvisionedThroughputDescription throughput_info = table_info.getProvisionedThroughput();
      System.out.println("Throughput");
      System.out.format(
          "  Read Capacity : %d\n", throughput_info.getReadCapacityUnits().longValue());
      System.out.format(
          "  Write Capacity: %d\n", throughput_info.getWriteCapacityUnits().longValue());

      List<AttributeDefinition> attributes = table_info.getAttributeDefinitions();
      System.out.println("Attributes");
      for (AttributeDefinition a : attributes) {
        System.out.format("  %s (%s)\n", a.getAttributeName(), a.getAttributeType());
      }
    } else {
      System.out.println("not table");
    }
  }

  @Override
  public void addItem(Data data) {
    System.out.println(data.getId() + "\t" + data.getName());
    aWSConfiguration.getDynamoDBMapper().save(data);

    /* Mapperを使わない場合はこちらを使う。できればMapperですべて解釈できるように設計したい。
     *    DynamoDB dynamoDB = new DynamoDB(aWSConfiguration.amazonDynamoDB());
    Table table = dynamoDB.getTable("Test");
    Item item = new Item().withPrimaryKey("Id", 10).withString("Name", "hogehoge");
    table.putItem(item);*/
  }

  public Data getItem(String id) {
    return aWSConfiguration.getDynamoDBMapper().load(Data.class, id);
  }

  public void updateItem(Data data) {
    Data movieLoad = aWSConfiguration.getDynamoDBMapper().load(Data.class, data.getId());
    if (null != movieLoad) aWSConfiguration.getDynamoDBMapper().save(data);
  }

  public void deleteItem(String id) {
    Data movie = aWSConfiguration.getDynamoDBMapper().load(Data.class, id);
    aWSConfiguration.getDynamoDBMapper().delete(movie);
  }
}
