package com.ceva.cfastbi.transcation.udt.dto;

import com.datastax.driver.mapping.annotations.UDT;

/**
 * orderItems class.
 * 
 * @author shaik
 *
 */
@UDT(name = "orderitems")
public class OrderItems {
  private String orderItemId;
  private String orderId;
  private String productId;
  private String quantity;


  public OrderItems() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * constructor.
   */
  public OrderItems(String orderItemId, String orderId, String productId, String quantity) {
    super();
    this.orderItemId = orderItemId;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
  }


  public String getOrderItemId() {
    return orderItemId;
  }


  public void setOrderItemId(String orderItemId) {
    this.orderItemId = orderItemId;
  }


  public String getOrderId() {
    return orderId;
  }


  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getProductId() {
    return productId;
  }


  public void setProductId(String productId) {
    this.productId = productId;
  }


  public String getQuantity() {
    return quantity;
  }


  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }


  @Override
  public String toString() {
    return "OrderItems [orderItemId=" + orderItemId + ", orderId=" + orderId + ", productId="
        + productId + ", quantity=" + quantity + "]";
  }



}
