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
public class ShipmentTrackNumberOutBound {

  private String shipmentId;
  private String shipmentStatus;


  public ShipmentTrackNumberOutBound() {
    super();
  }

  /**
   * Constructor.
   * 
   */
  public ShipmentTrackNumberOutBound(String shipmentId,  String carrierInformation,
      String shipmentStatus, LocalDate expectedDeliveryDate) {
    super();
    this.shipmentId = shipmentId;
    this.shipmentStatus = shipmentStatus;
  }




  public String getShipmentId() {
    return shipmentId;
  }

  public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
  }


  public String getShipmentStatus() {
    return shipmentStatus;
  }

  public void setShipmentStatus(String shipmentStatus) {
    this.shipmentStatus = shipmentStatus;
  }


}
