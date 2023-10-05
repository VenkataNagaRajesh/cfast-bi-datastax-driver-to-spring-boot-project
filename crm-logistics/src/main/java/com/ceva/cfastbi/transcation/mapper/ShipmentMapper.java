package com.ceva.cfastbi.transcation.mapper;

import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import com.ceva.cfastbi.transcation.udt.dto.Shipment;
import com.ceva.cfastbi.transcation.udt.inbound.ShipmentInBound;
import com.datastax.driver.core.LocalDate;

/**
 * Mapping to ShipementDetials Object.
 * 
 * @author rajesh
 *
 */
@Component
public class ShipmentMapper {

  /**
   * Setting fields to ShipmentObject.
   * 
   * @return shipmentObject.
   */
  public static Shipment mappingtoShipmentDto(ShipmentInBound shipmentInBound) {
    Shipment shipmentDetailsDto = new Shipment();

    shipmentDetailsDto.setShipmentId(shipmentIdGen(shipmentInBound));

    Date deliveryDate = DateUtils.addDays(new Date(), 7);
    LocalDate expectedDeliveryDate = LocalDate.fromMillisSinceEpoch(deliveryDate.getTime());
    shipmentDetailsDto.setExpectedDeliveryDate(expectedDeliveryDate);
    shipmentDetailsDto.setCarrierInformation(shipmentInBound.getCarrierInformation());
    shipmentDetailsDto.setOrderId(shipmentInBound.getOrderId());
    shipmentDetailsDto.setShipmentStatus(shipmentInBound.getShipmentStatus());
    shipmentDetailsDto.setTrackingNumber(shipmentInBound.getTrackingNumber());
    return shipmentDetailsDto;
  }

  /**
   * ShipmentId Generator.
   */
  public static String shipmentIdGen(ShipmentInBound shipmentInBound) {
    String s1 = "S" + RandomStringUtils.randomAlphabetic(6);
    if (shipmentInBound.getShipmentId() == null || shipmentInBound.getShipmentId() == "null") {
      return s1;
    } else {
      System.out.println(shipmentInBound.getShipmentId());
      return shipmentInBound.getShipmentId();

    }
  }
}
