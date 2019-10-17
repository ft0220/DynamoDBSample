package com.sample.dynamodb;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.dynamodb.model.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response implements Serializable {

  private static final long serialVersionUID = 8526747595651537720L;

  private String message;

  private Data data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
