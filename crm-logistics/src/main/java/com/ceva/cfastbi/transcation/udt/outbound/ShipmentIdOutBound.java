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
public class ShipmentIdOutBound {

  private String shipmentId;
  private String carrierInformation;
  private String shipmentStatus;
  private LocalDate expectedDeliveryDate;


  public ShipmentIdOutBound() {
    super();
  }

  /**
   * Constructor.
   * 
   */
  public ShipmentIdOutBound(String shipmentId,  String carrierInformation,
      String shipmentStatus, LocalDate expectedDeliveryDate) {
    super();
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.shipmentId = shipmentId;
    this.carrierInformation = carrierInformation;
    this.shipmentStatus = shipmentStatus;
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


}
