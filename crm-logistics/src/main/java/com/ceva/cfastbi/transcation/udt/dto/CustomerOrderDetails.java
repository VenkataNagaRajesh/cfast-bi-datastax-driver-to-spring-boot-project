package com.ceva.cfastbi.transcation.udt.dto;

import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * customer ordering details.
 * 
 * @author shaik.
 *
 */
@Table(name = "customerorderdetails")
public class CustomerOrderDetails {
  @PartitionKey
  private String customerId;
  private Customer customer;
  private OrderItems orderitems;
  private Addresses addresses;


  public CustomerOrderDetails() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * constructor.
   */
  public CustomerOrderDetails(String customerId, Customer customer, OrderItems orderitems,
      Addresses addresses) {
    super();
    this.customerId = customerId;
    this.customer = customer;
    this.orderitems = orderitems;
    this.addresses = addresses;
  }


  public String getCustomerId() {
    return customerId;
  }


  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }


  public Customer getCustomer() {
    return customer;
  }


  public void setCustomer(Customer customer) {
    this.customer = customer;
  }


  public OrderItems getOrderitems() {
    return orderitems;
  }


  public void setOrderitems(OrderItems orderitems) {
    this.orderitems = orderitems;
  }


  public Addresses getAddresses() {
    return addresses;
  }


  public void setAddresses(Addresses addresses) {
    this.addresses = addresses;
  }


  @Override
  public String toString() {
    return "CustomerOrderDetails [customerId=" + customerId + ", customer=" + customer
        + ", orderitems=" + orderitems + ", addresses=" + addresses + "]";
  }


}
