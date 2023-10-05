package com.ceva.cfastbi.transcation.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ceva.cfastbi.transcation.datastax.Connection;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.udt.outbound.Customer;
import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;


@Repository
public class OrderRepositoryImplementation implements OrderRepository {
  private static Logger logger = LoggerFactory.getLogger(OrderRepository.class);
  @Autowired
  Connection connection;

  @Override
  public void order(com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails orderdetails) {
    logger.info("recieved request from service for storing customerorderdertails data string");
    MappingManager manager = connection.getManager();
    Mapper<com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails> map =
        manager.mapper(com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails.class);
    map.save(orderdetails);
  }

  @Override
  public List<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> orderedList() {
    logger.info(
        "Reached request to repository orderedlist method to fetch all records from database");
    Select select = QueryBuilder.select().all().from("CustomerOrderDetails");
    ResultSet resultset = connection.getSession().execute(select);
    MappingManager manager = connection.getManager();
    Mapper<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> map =
        manager.mapper(com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails.class);
    Result<com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails> result =
        map.map(resultset);
    return result.all();

  }

  @Override
  public List<CustomerOrderDetails> orderByCustomerId(String customerid)
      throws NoDataFoundException {
    logger.info("recieved to the repository for frtching the customerorderdetails by id");
    CustomerOrderDetails li = getAccessor().getById(customerid).one();
    String check = li == null ? "null" : "notnull";
    if (!check.equals("null")) {

      return getAccessor().getById(customerid).all();
    } else {
      throw new NoDataFoundException("not found for a given id");
    }

  }

  @Override
  public OrderAccessor getAccessor() {
    return connection.getManager().createAccessor(OrderAccessor.class);
  }

  @Override
  public CustomerOrderDetails orderById(String customerid) {
    // TODO Auto-generated method stub
    Select select = QueryBuilder.select().all().from("customerorderdetails");
    select.where(QueryBuilder.eq("customerid", customerid));
    ResultSet resultSet = connection.getSession().execute(select);
    MappingManager manager = connection.getManager();
    Mapper<CustomerOrderDetails> map = manager.mapper(CustomerOrderDetails.class);
    CustomerOrderDetails re = map.get(customerid);
    return re;
  }

  @Override
  public String deleteByCustomerId(String customerid) throws NoDataFoundException {
    // TODO Auto-generated method stub
    logger.info("reached to repository for deleting record by id");
    Select selectingrecord = QueryBuilder.select().all().from("customerorderdetails");
    selectingrecord.where(QueryBuilder.eq("customerid", customerid));
    ResultSet resultset = connection.getSession().execute(selectingrecord);
    MappingManager manager = connection.getManager();
    Mapper<com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails> map =
        manager.mapper(com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails.class);
    com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails result = map.get(customerid);
    String check = result == null ? "null" : "notnull";
    if (!check.equals("null")) {
      map.delete(customerid);
      return "deleted";
    } else {
      throw new NoDataFoundException("no data found for a given id");
    }
  }



}
