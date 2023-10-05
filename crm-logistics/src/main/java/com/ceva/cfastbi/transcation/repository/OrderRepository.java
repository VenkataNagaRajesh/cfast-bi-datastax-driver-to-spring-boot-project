package com.ceva.cfastbi.transcation.repository;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.mapping.Result;

public interface OrderRepository {

  public void order(com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails orderdetails);

  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderedList();

  public List<CustomerOrderDetails> orderByCustomerId(String customerid);

  public CustomerOrderDetails orderById(String customerid);

  public OrderAccessor getAccessor();
  
  public String deleteByCustomerId(String customerid);
  
}
