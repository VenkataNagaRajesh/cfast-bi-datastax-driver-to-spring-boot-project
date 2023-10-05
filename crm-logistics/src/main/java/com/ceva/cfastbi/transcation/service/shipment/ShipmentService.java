package com.ceva.cfastbi.transcation.service.shipment;

import java.util.List;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.udt.inbound.ShipmentInBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;

/**
 * ShipmentService Interface.
 * @author rajesh
 *
 */
public interface ShipmentService {

  public void saveShipmentDetails(ShipmentInBound shipmentInBoundDetails);
  
  public void updateShipmentDetails(ShipmentInBound shipmentIBound);
  
  public List<ShipmentOutBound> getShipmentDetails();
  
  public List<ShipmentOutBound> getShipmentDetails(String id) throws Exception;
  
  public List<ShipmentHistoryOutBound> getShipmentHistory(String id)throws Exception;
  
  public List<ShipmentIdOutBound> getShipmentDetailsByShipmentId(String id) throws NoDataFoundException;
  
  public List<ShipmentTrackNumberOutBound> getShipmentDetailsByTrackingNumber(String id)throws NoDataFoundException;
  
}
