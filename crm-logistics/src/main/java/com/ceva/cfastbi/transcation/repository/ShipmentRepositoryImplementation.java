package com.ceva.cfastbi.transcation.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ceva.cfastbi.transcation.datastax.Connection;
import com.ceva.cfastbi.transcation.exception.NoDataFoundException;
import com.ceva.cfastbi.transcation.mapper.ShipmentHistoryMapper;
import com.ceva.cfastbi.transcation.udt.dto.Shipment;
import com.ceva.cfastbi.transcation.udt.dto.ShipmentHistory;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentHistoryOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentIdOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentOutBound;
import com.ceva.cfastbi.transcation.udt.outbound.ShipmentTrackNumberOutBound;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

/**
 * ShipmentRepository Implemetation.
 * 
 * @author rajesh
 *
 */
@Repository
public class ShipmentRepositoryImplementation implements ShipmentRepository {

  @Autowired
  Connection connection;

  Logger logger = LoggerFactory.getLogger(ShipmentRepositoryImplementation.class);

  /**
   * Saving shipmentDetails.
   */
  @Override
  public void saveShipmentDetails(Shipment shipmentDetails) {
    logger.info("In shipment repository save shipment detials method invoked");
    MappingManager manager = connection.getManager();
    Mapper<Shipment> map = manager.mapper(Shipment.class);
    logger.info("Data saving proceess");
    map.save(shipmentDetails);
    ShipmentHistory shipmentHisotryDetails =
        ShipmentHistoryMapper.shipmentHistoryMapper(shipmentDetails);
    saveShipmentHistoryDetails(shipmentHisotryDetails);

  }

  /**
   * getting ShipmentDetails.
   */
  @Override
  public List<ShipmentOutBound> getShipmentDetails() {
    // Select select = QueryBuilder.select().all().from("shipment");
    ResultSet resultset = connection.getSession().execute("select *from shipment");
    MappingManager manager = connection.getManager();
    Mapper<ShipmentOutBound> mapper = manager.mapper(ShipmentOutBound.class);
    Result<ShipmentOutBound> result = mapper.map(resultset);
    return result.all();
  }

  /**
   * getting ShipmentDetails OrderId.
   */
  @Override
  public List<ShipmentOutBound> getShipmentDetailsByOrderId(String id) throws NoDataFoundException {
    ShipmentOutBound shipmentOutBound = new ShipmentOutBound();
    ResultSet resultset = connection.getSession()
        .execute("select *from shipment where orderid = ? ALLOW FILTERING", id);
    Row row = resultset.one();
    if (row != null) {
      shipmentOutBound.setCarrierInformation(row.getString("carrierinformation"));
      shipmentOutBound.setExpectedDeliveryDate(row.getDate("expecteddeliverydate"));
      shipmentOutBound.setOrderId(row.getString("orderid"));
      shipmentOutBound.setShipmentId(row.getString("shipmentid"));
      shipmentOutBound.setShipmentStatus(row.getString("shipmentstatus"));
      shipmentOutBound.setTrackingNumber(row.getString("trackingnumber"));
      return null;
    } else {
      throw new NoDataFoundException("Data is not found with this given id");
    }
  }


  public ShipmentAccessor getData() {
    return connection.getManager().createAccessor(ShipmentAccessor.class);
  }

  /**
   * gettingShipmentDetails by shipmentId.
   */
  @Override
  public List<ShipmentIdOutBound> getShipmentDetailsByShipmentId(String id)
      throws NoDataFoundException {
    logger.info("data fetching strted");
    ShipmentIdOutBound shipmentOutBoundata = getData().getShipmentDataByShipmentId(id).one();
    System.out.println("data fechting");
    if (!shipmentOutBoundata.equals("null") || !shipmentOutBoundata.toString().isEmpty()) {
      logger.info("date fetched");
      return getData().getShipmentDataByShipmentId(id).all();
    } else {
      throw new NoDataFoundException("not found for a given id");
    }
  }


  @Override
  public List<ShipmentTrackNumberOutBound> getShipmentDetailsByTrackingNumber(String id)
      throws NoDataFoundException {
    ShipmentTrackNumberOutBound shipmentTrackingNumberData =
        getData().getShipmentTrackNumber(id).one();
    if (!shipmentTrackingNumberData.equals("null")
        || shipmentTrackingNumberData.toString().isEmpty()) {
      return getData().getShipmentTrackNumber(id).all();
    } else {
      throw new NoDataFoundException("Data is not Found with this id");
    }
  }

  /**
   * Saving Shipment history Details.
   */
  @Override
  public void saveShipmentHistoryDetails(ShipmentHistory shipmentHistoryDetails) {
    logger.info("In shipment repository save shipmenthistory details method invoked");
    MappingManager manager = connection.getManager();
    Mapper<ShipmentHistory> map = manager.mapper(ShipmentHistory.class);
    logger.info("Data saving proceess");
    map.save(shipmentHistoryDetails);

  }

  /**
   * ShipmentHistory Details.
   */
  @Override
  public List<ShipmentHistoryOutBound> getShipmentHistoryDetials(String id)
      throws NoDataFoundException {
    return getData().getShipmentHistoryData(id).all();

  }
}
