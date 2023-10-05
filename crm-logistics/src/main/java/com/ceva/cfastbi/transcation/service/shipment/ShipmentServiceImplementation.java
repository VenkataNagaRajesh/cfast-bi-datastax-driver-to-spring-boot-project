package com.ceva.cfastbi.transcation.service.shipment;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.mapper.ShipmentMapper;
import com.ceva.cfastbi.transcation.repository.ShipmentRepository;
import com.ceva.cfastbi.transcation.udt.dto.Shipment;
import com.ceva.cfastbi.transcation.udt.inbound.ShipmentInBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;

/**
 * ShipmentService implementation.
 * 
 * @author rajesh
 *
 */
@Service
public class ShipmentServiceImplementation implements ShipmentService {

  @Autowired
  ShipmentRepository shipmentrepo;

  Logger logger = LoggerFactory.getLogger(ShipmentServiceImplementation.class);

  @Override
  public void saveShipmentDetails(ShipmentInBound shipmentInBoundDetails) {
    logger.info("In shipment service saveShipmentDetails method invoked");
    Shipment shipmentDetailsDto = ShipmentMapper.mappingtoShipmentDto(shipmentInBoundDetails);
    shipmentrepo.saveShipmentDetails(shipmentDetailsDto);
  }

  @Override
  public List<ShipmentOutBound> getShipmentDetails() {
    return shipmentrepo.getShipmentDetails();
  }

  @Override
  public List<ShipmentOutBound> getShipmentDetails(String id) throws Exception {
    return shipmentrepo.getShipmentDetailsByOrderId(id);
  }

  @Override
  public List<ShipmentIdOutBound> getShipmentDetailsByShipmentId(String id)
      throws NoDataFoundException {
    return shipmentrepo.getShipmentDetailsByShipmentId(id);
  }

  @Override
  public void updateShipmentDetails(ShipmentInBound shipmentInBoundDetails) {
    logger.info("In shipment Service update Shipmentdetials mehtod invoked");
    try {
      List<ShipmentOutBound> shipmentDetials =
          shipmentrepo.getShipmentDetailsByOrderId(shipmentInBoundDetails.getOrderId());
      logger.info("printing shipmentetails outbound data");

      for (ShipmentOutBound item : shipmentDetials) {
        if (shipmentInBoundDetails.getShipmentId().equals(item.getShipmentId())
            || shipmentInBoundDetails.getOrderId().equals(item.getOrderId())) {
          Shipment shipmentDetialsDto = ShipmentMapper.mappingtoShipmentDto(shipmentInBoundDetails);
          shipmentrepo.saveShipmentDetails(shipmentDetialsDto);
        }
      }
    } catch (Exception e) {
      throw new NoDataFoundException("Shipment Id is not found to update plese enter a valid id");
    }
  }

  @Override
  public List<ShipmentTrackNumberOutBound> getShipmentDetailsByTrackingNumber(String id)
      throws NoDataFoundException {

    return shipmentrepo.getShipmentDetailsByTrackingNumber(id);
  }


  public List<ShipmentHistoryOutBound> getShipmentHistory(String id) {
    System.out.println(id);
    return shipmentrepo.getShipmentHistoryDetials(id);
  }


}
