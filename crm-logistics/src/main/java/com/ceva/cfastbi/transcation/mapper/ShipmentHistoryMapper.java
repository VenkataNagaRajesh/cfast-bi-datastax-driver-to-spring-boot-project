package com.ceva.cfastbi.transcation.mapper;

import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.RandomStringUtils;
import com.ceva.cfastbi.transcation.udt.dto.Shipment;
import com.ceva.cfastbi.transcation.udt.dto.ShipmentHistory;
import com.datastax.driver.core.LocalDate;

/**
 * ShipmentHistoryMapper.
 * 
 * @author rajesh
 *
 */
public class ShipmentHistoryMapper {

  /**
   * ShipmentHistory Mapper.
   */
  public static ShipmentHistory shipmentHistoryMapper(Shipment shipmentInBound) {
    ShipmentHistory shipmentHistory = new ShipmentHistory();
    shipmentHistory.setEventType(shipmentInBound.getShipmentStatus());
    shipmentHistory.setShipmentId(shipmentInBound.getShipmentId());


    if (shipmentHistory.getShipmenthistoryid() == null
        || shipmentHistory.getShipmenthistoryid() == "null") {
      shipmentHistory.setShipmenthistoryid("SMH" + RandomStringUtils.randomAlphanumeric(6));
    } else {
      shipmentHistory.setShipmenthistoryid(shipmentHistory.getShipmenthistoryid());
    }


    Date javaUtilDate = new Date();
    System.out.println(javaUtilDate);
    shipmentHistory.setEventDate(LocalDate.fromMillisSinceEpoch(javaUtilDate.getTime()));

    return shipmentHistory;
  }
}
