package com.sample.dynamodb.repositories;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.sample.dynamodb.model.Data;

@EnableScan
public interface DataRepository extends CrudRepository<Data, String> {
  List<Data> findByName(String name);

  List<Data> findByid(String id);
}
