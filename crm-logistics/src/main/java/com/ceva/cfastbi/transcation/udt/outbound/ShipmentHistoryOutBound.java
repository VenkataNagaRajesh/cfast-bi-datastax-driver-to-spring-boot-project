package com.ceva.cfastbi.transcation.udt.outbound;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * ShipmentHistory OutBoundClass.
 * 
 * @author rajesh
 *
 */
@Table(name = "shipmenthistory")
public class ShipmentHistoryOutBound {

  @PartitionKey
  private String shipmenthistoryid;
  private String shipmentid;
  private LocalDate eventdate;
  private String eventtype;


  public String getShipmenthistoryId() {
    return shipmenthistoryid;
  }

  public void setShipmenthistoryId(String shipmenthistoryid) {
    this.shipmenthistoryid = shipmenthistoryid;
  }

  public String getShipmentId() {
    return shipmentid;
  }

  public void setShipmentId(String shipmentid) {
    this.shipmentid = shipmentid;
  }

  public LocalDate getEventDate() {
    return eventdate;
  }

  public void setEventDate(LocalDate eventdate) {
    this.eventdate = eventdate;
  }

  public String getEventType() {
    return eventtype;
  }

  public void setEventType(String eventtype) {
    this.eventtype = eventtype;
  }


}
