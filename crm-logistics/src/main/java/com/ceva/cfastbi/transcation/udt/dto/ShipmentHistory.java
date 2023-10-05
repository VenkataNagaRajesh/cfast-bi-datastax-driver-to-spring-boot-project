package com.ceva.cfastbi.transcation.udt.dto;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Table;

/**
 * ShipmentHistory class.
 * 
 * @author rajesh
 *
 */
@Table(name = "shipmenthistory")
public class ShipmentHistory {

  private String shipmenthistoryid;
  private String shipmentId;
  private LocalDate eventDate;
  private String eventType;


  public String getShipmenthistoryid() {
    return shipmenthistoryid;
  }

  public void setShipmenthistoryid(String shipmenthistoryid) {
    this.shipmenthistoryid = shipmenthistoryid;
  }

  public String getShipmentId() {
    return shipmentId;
  }

  public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }



}
