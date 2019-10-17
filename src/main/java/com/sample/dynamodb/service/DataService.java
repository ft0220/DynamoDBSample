package com.sample.dynamodb.service;

import com.sample.dynamodb.model.Data;

public interface DataService {

  public void getDescribe();

  public Data getItem(String id);

  public void addItem(Data data);

  public void updateItem(Data data);

  public void deleteItem(String id);
}
