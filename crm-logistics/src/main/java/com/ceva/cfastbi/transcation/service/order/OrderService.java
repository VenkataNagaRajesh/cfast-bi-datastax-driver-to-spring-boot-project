package com.ceva.cfastbi.transcation.service.order;

import java.util.List;
import com.ceva.cfastbi.transcation.udt.outbound.Customer;
import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.mapping.Result;

public interface OrderService {

  public void order(
      com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails customerorderdetails);

  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderedList();

  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderByCuistomerId(
      String id);

  public String deleteById(String customerid);
}
