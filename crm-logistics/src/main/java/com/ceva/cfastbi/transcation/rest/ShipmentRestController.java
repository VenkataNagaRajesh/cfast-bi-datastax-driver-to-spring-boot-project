package com.ceva.cfastbi.transcation.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.common.CommonUtills;
import com.ceva.cfastbi.transcation.exception.InvalidInputFormat;
import com.ceva.cfastbi.transcation.service.shipment.ShipmentService;
import com.ceva.cfastbi.transcation.udt.inbound.ShipmentInBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ShipmentRestController.
 * 
 * @author rajesh
 *
 */
@RestController
public class ShipmentRestController {

  @Autowired
  ShipmentService shipmentService;

  Logger logger = LoggerFactory.getLogger(ShipmentRestController.class);

  /**
   * Posting shipmentDetails.
   */
  @PostMapping("/saveShipmentDetails")
  public String savingShipmentDetials(@RequestBody String shipmentInBoundDetials) throws Exception {
    logger.info("save shipmentDetials method invoked");
    if (CommonUtills.isJsonOrNot(shipmentInBoundDetials) == true) {
      ShipmentInBound shipmentDetailsInbound =
          new ObjectMapper().readValue(shipmentInBoundDetials, ShipmentInBound.class);
      logger.info("mapper done");
      shipmentService.saveShipmentDetails(shipmentDetailsInbound);
      return shipmentInBoundDetials;
    } else {
      throw new InvalidInputFormat("not a valid json");
    }
  }

  /**
   * Getting ShipmentDetails.
   */
  @GetMapping("/getShipmentDetails")
  public List<ShipmentOutBound> getShipmentDetails() {
    return shipmentService.getShipmentDetails();
  }

  /**
   * getting ShipmentDetials by oderid.
   * 
   */
  @GetMapping("/getShipmentDetailsByOrderId/{id}")
  public List<ShipmentOutBound> getShipmentDetailsByOrderId(@PathVariable String id)
      throws Exception {
    return shipmentService.getShipmentDetails(id);
  }

  /**
   * getting ShipmentDetials by shipmentid.
   * 
   */
  @GetMapping("/getShipmentDetailsByShipmentId/{id}")
  public List<ShipmentIdOutBound> getShipmentDetailsByShipmentId(@PathVariable String id)
      throws Exception {
    return shipmentService.getShipmentDetailsByShipmentId(id);
  }

  /**
   * getting ShipmentDetails by tracking nunmber.
   */
  @GetMapping("/getShipmentDetailsByTrackingNumber/{id}")
  public List<ShipmentTrackNumberOutBound> getShipmentDetailsByTrackNumber(@PathVariable String id)
      throws Exception {
    return shipmentService.getShipmentDetailsByTrackingNumber(id);
  }

  /**
   * update the shipmentdetails.
   */
  @PutMapping("/updateShipmentDetails")
  public ShipmentInBound updateShipmentDetails(
      @RequestBody ShipmentInBound shipmentInBoundeDetails) {
    logger.info("update shipmentDetials method invoked");
    shipmentService.updateShipmentDetails(shipmentInBoundeDetails);
    return shipmentInBoundeDetails;
  }

  /**
   * getting ShipmentHistory.
   */
  @GetMapping("/getShipmentHistoryByShipmentId/{id}")
  public List<ShipmentHistoryOutBound> getShipmentHistory(@PathVariable String id)
      throws Exception {
    System.out.println(id);
    return shipmentService.getShipmentHistory(id);
  }
}
