package com.ceva.cfastbi.transcation.repository;

import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * Native Query.
 * 
 * @author rajesh
 *
 */
@Accessor
public interface ShipmentAccessor {

  @Query("=:id ALLOW FILTERING")
  public Result<ShipmentOutBound> getShipmentDetialsByOrderid(@Param("id") String id);

  @Query("select shipmentid,shipmentstatus,carrierinformation,expecteddeliverydate from shipment where shipmentid=:shipmentid ALLOW FILTERING")
  public Result<ShipmentIdOutBound> getShipmentDataByShipmentId(
      @Param("shipmentid") String shipmentid);

  @Query("select shipmentid,shipmentstatus,trackingnumber from shipment where trackingnumber=:trackingnumber ALLOW FILTERING")
  public Result<ShipmentTrackNumberOutBound> getShipmentTrackNumber(
      @Param("trackingnumber") String trackNumber);

  @Query("select * from shipmenthistory where shipmentid=:id ALLOW FILTERING")
  public Result<ShipmentHistoryOutBound> getShipmentHistoryData(@Param("id") String id);

}
