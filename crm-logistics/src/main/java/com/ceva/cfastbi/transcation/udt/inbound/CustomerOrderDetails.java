package com.ceva.cfastbi.transcation.udt.inbound;

import com.datastax.driver.mapping.annotations.Table;

/**
 * customer ordering details.
 * 
 * @author shaik.
 *
 */

public class CustomerOrderDetails {
  private String customerid;
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
  public CustomerOrderDetails(String customerid, Customer customer, OrderItems orderitems,
      Addresses addresses) {
    super();
    this.customerid = customerid;
    this.customer = customer;
    this.orderitems = orderitems;
    this.addresses = addresses;
  }


  public String getcustomerid() {
    return customerid;
  }


  public void setcustomerid(String customerid) {
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
