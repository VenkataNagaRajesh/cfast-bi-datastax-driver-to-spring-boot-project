package com.ceva.cfastbi.transcation.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaTypeEditor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.common.CommonUtills;
import com.ceva.cfastbi.transcation.exception.InvalidInputFormat;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.service.order.OrderService;
import com.ceva.cfastbi.transcation.udt.outbound.Customer;
import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.mapping.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.net.MediaType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


/**
 * Rest controller.
 * 
 * @author shaik
 *
 */
@RestController
public class OrderRestController {

  private static Logger logger = LoggerFactory.getLogger(OrderRestController.class);

  @Autowired
  OrderService orderservice;

  /**
   * Posting order details.
   */
  @PostMapping("/order")
  public String requestOrder(@org.springframework.web.bind.annotation.RequestBody String order)
      throws JsonMappingException, JsonProcessingException, InvalidInputFormat {
    logger.info("Recieved request to the rest");
    if (CommonUtills.isJsonOrNot(order) == true) {
      System.out.println("comming");
      ObjectMapper mapper = new ObjectMapper();
      System.out.println("hkjhkl=------");
      com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails customerorderdetails = mapper
          .readValue(order, com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails.class);
      System.out.println(customerorderdetails);
      orderservice.order(customerorderdetails);
      return order;
    } else {
      throw new InvalidInputFormat("not a valid json");
    }


  }

  /**
   * getting order details.
   */
  @GetMapping("/orders")
  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> requestOrderedList() {
    List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> customerorderdetails =
        orderservice.orderedList();
    return customerorderdetails;
  }

  /**
   * get by id.
   */
  @GetMapping("/orderbyid/{customerid}")
  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderById(
      @PathVariable String customerid) {
    logger.info("recieved to the rest endpoint ");
    return orderservice.orderByCuistomerId(customerid);
  }

  /**
   * Updating order details.
   */
  @PutMapping("/order")
  public com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails updateOrder(
      @org.springframework.web.bind.annotation.RequestBody com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails customerorderdetails) {
    logger.info("Recieved to rest for update customerorderdetails");
    orderservice.order(customerorderdetails);
    return customerorderdetails;
  }

  /**
   * Delete by id.
   */
  @DeleteMapping("/order/{customerid}")
  public String deleteById(@PathVariable String customerid) {
    logger.info("reached rest");

    return orderservice.deleteById(customerid);
  }
}
