package com.ceva.cfastbi.transcation.repository;

import java.util.List;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.udt.dto.Shipment;
import com.ceva.cfastbi.transcation.udt.dto.ShipmentHistory;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;

/**
 * ShipmentRepository.
 * @author rajesh
 *
 */
public interface ShipmentRepository {

  public void saveShipmentDetails(Shipment shipmentDetails);
  
  public void saveShipmentHistoryDetails(ShipmentHistory shipmentHistoryDetails);
  
  public List<ShipmentOutBound> getShipmentDetails();
  
  public List<ShipmentOutBound> getShipmentDetailsByOrderId(String id) throws NoDataFoundException;
  
  public List<ShipmentIdOutBound> getShipmentDetailsByShipmentId(String id)throws NoDataFoundException;
  
  public List<ShipmentTrackNumberOutBound> getShipmentDetailsByTrackingNumber(String id)throws NoDataFoundException;
  
  public List<ShipmentHistoryOutBound> getShipmentHistoryDetials(String id)throws NoDataFoundException;
  
}
