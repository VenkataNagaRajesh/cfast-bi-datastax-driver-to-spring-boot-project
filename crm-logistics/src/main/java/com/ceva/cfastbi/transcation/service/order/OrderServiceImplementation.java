package com.ceva.cfastbi.transcation.service.order;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceva.cfastbi.transcation.mapper.CustomerOrderDetailsMapping;
import com.ceva.cfastbi.transcation.repository.OrderRepository;
import com.ceva.cfastbi.transcation.repository.OrderRepositoryImplementation;
import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.mapping.Result;

@Service
public class OrderServiceImplementation implements OrderService {
  public static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderService.class);
  @Autowired
  OrderRepository orderrepository;

  @Override
  public void order(com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails customerorderdetails) {
    logger.info("Recieved request to the service layer");
    com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails customerorderdetailsdto =
        CustomerOrderDetailsMapping.curtomerOrderDetailsMapping(customerorderdetails);
    orderrepository.order(customerorderdetailsdto);
  }

  @Override
  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderedList() {
    logger.info("Recieved request for getting all ordered details of customer to service");
    return orderrepository.orderedList();

  }

  @Override
  public List<CustomerOrderDetails> orderByCuistomerId(String customerid) {
    logger.info("recieved  from rest to service layer");
    return orderrepository.orderByCustomerId(customerid);
  }

  @Override
  public String deleteById(String customerid) {
    // TODO Auto-generated method stub
    logger.info("reached service");
    return orderrepository.deleteByCustomerId(customerid);
  }

}
