package com.ceva.cfastbi.transcation.udt.inbound;

import java.util.Date;

/**
 * Order details.
 * 
 * @author shaik
 *
 */
public class Order {
  private String orderId;
  private String customerId;
  private Date orderDate;
  private String status;

  /**
   * Constructor.
   */
  public Order(String orderId, String customerId, Date orderDate, String status) {
    super();
    this.orderId = orderId;
    this.customerId = customerId;
    this.orderDate = orderDate;
    this.status = status;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Order [orderId=" + orderId + ", customerId=" + customerId + ", orderDate=" + orderDate
        + ", status=" + status + "]";
  }

}
