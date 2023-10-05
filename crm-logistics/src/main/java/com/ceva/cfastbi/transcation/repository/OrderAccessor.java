package com.ceva.cfastbi.transcation.repository;

import com.ceva.cfastbi.transcation.udt.outbound.CustomerOrderDetails;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * Native query .
 * 
 * @author shaik.
 *
 */
@Accessor
public interface OrderAccessor {

  @Query("select * from customerorderdetails where customerid=:customerid")
  public Result<CustomerOrderDetails> getById(@Param("customerid") String customerid);
  
}
