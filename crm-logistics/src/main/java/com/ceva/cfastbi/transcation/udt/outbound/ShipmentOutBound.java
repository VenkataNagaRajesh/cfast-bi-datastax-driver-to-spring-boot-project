package com.ceva.cfastbi.transcation.udt.outbound;

import java.util.Date;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Table;

/**
 * ShipmentOutBoundDetials.
 * 
 * @author rajesh
 *
 */
@Table(name = "shipment")
public class ShipmentOutBound {

  private String shipmentId;
  private String orderId;
  private String carrierInformation;
  private String shipmentStatus;
  private String trackingNumber;
  private LocalDate expectedDeliveryDate;
 

  public ShipmentOutBound() {
    super();
  }

  /**
   * Constructor.
   * 
   */
  public ShipmentOutBound(String shipmentId,String orderId, String carrierInformation, String shipmentStatus,
      String trackingNumber,LocalDate expectedDeliveryDate) {
    super();
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.shipmentId = shipmentId;
    this.orderId = orderId;
    this.carrierInformation = carrierInformation;
    this.shipmentStatus = shipmentStatus;
    this.trackingNumber = trackingNumber;
  }




  public LocalDate getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }

  
  public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public String getShipmentId() {
    return shipmentId;
  }

  public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
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

  @Override
  public String toString() {
    return "ShipmentOutBound [shipmentId=" + shipmentId + ", orderId=" + orderId
        + ", carrierInformation=" + carrierInformation + ", shipmentStatus=" + shipmentStatus
        + ", trackingNumber=" + trackingNumber + ", expectedDeliveryDate=" + expectedDeliveryDate
        + "]";
  }

}
