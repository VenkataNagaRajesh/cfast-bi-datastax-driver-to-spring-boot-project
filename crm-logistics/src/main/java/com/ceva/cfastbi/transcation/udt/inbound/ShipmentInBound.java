package com.ceva.cfastbi.transcation.udt.inbound;

import java.sql.Date;

/**
 * ShipmentDetials InBound.
 * 
 * @author rajesh.
 *
 */
public class ShipmentInBound {

  private String shipmentId;
  private String orderId;
  private String carrierInformation;
  private String shipmentStatus;
  private String trackingNumber;
  private Date expectedDeliveryDate;

  public Date getExpetedDeliveryDate() {
    return expectedDeliveryDate;
  }

  public void setExpetedDeliveryDate(Date expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getShipmentId() {
    return shipmentId;
  }


  public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
  }


  public Date getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }


  public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public String getCarrierInformation() {
    return carrierInformation;
  }

  public void setCarrierInformation(String carrierInformation) {
    this.carrierInformation = carrierInformation;
  }

  public String getShipmentStatus() {
    return shipmentStatus;
  }

  public void setShipmentStatus(String shipmentStatus) {
    this.shipmentStatus = shipmentStatus;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

}
