package com.ceva.cfastbi.transcation.udt.outbound;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.UDT;

/**
 * customer ordering details.
 * 
 * @author shaik.
 *
 */

@Table(name = "CustomerOrderDetails")
public class CustomerOrderDetails {
  @PartitionKey
  private String customerid;

  private Customer customer;

  private OrderItems orderitems;

  private Addresses addresses;


  /**
   * constructor.
   */
  public CustomerOrderDetails() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructor.
   * 
   */
  public CustomerOrderDetails(String customerid, Customer customer, OrderItems orderitems,
      Addresses addresses) {
    super();
    this.customerid = customerid;
    this.customer = customer;
    this.orderitems = orderitems;
    this.addresses = addresses;
  }


  public String getCustomerid() {
    return customerid;
  }


  public void setCustomerid(String customerid) {
    this.customerid = customerid;
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
    return "CustomerOrderDetails [customerid=" + customerid + ", customer=" + customer
        + ", orderitems=" + orderitems + ", addresses=" + addresses + "]";
  }



}
