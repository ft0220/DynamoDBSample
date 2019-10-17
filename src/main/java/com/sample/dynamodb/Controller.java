package com.sample.dynamodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sample.dynamodb.model.Data;
import com.sample.dynamodb.service.DataService;

@RestController
public class Controller {

  @Autowired DataService movieServiceImpl;

  @GetMapping(path = "/hello")
  public ResponseEntity<String> getHello() {

    return ResponseEntity.ok("<html><body><b1>Hello Project</b1></body></html>");
  }

  @GetMapping(path = "/get")
  public ResponseEntity<Response> getDetails(@RequestParam("id") String id) {
    Data movie = movieServiceImpl.getItem(id);
    if (movie != null) {
      Response response = new Response();
      response.setData(movie);
      response.setMessage("Success");
      return ResponseEntity.ok(response);
    } else {
      Response response = new Response();
      response.setMessage("No Data FOund");
      ResponseEntity<Response> responseEntity =
          new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
      return responseEntity;
    }
  }

  @PostMapping(path = "/add")
  public ResponseEntity<String> addDetails(@RequestBody Data movie) {
    movieServiceImpl.addItem(movie);
    return ResponseEntity.ok("Success");
  }

  @PutMapping(path = "/update")
  public ResponseEntity<String> updateDetails(@RequestBody Data movie) {
    movieServiceImpl.updateItem(movie);
    return ResponseEntity.ok("Success");
  }

  @DeleteMapping(path = "/delete")
  public ResponseEntity<String> deleteDetails(@RequestParam("id") String id) {
    movieServiceImpl.deleteItem(id);
    return ResponseEntity.ok("Success");
  }
}
