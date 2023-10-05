/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceva.cfastbi.transcation.actuator.CjfMeterService;

/**
 * Implementation of the Prometheus service.
 * 
 * @author Raul Garnacho
 */
@Service
public class PrometheusServiceImpl implements PrometheusService {
  
  @Autowired
  private CjfMeterService cjfMeterService;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(PrometheusServiceImpl.class);

  @Override
  public void monitorTransactions() {
    LOGGER.info("monitorTransactions method");
    cjfMeterService.transactionsInMethod("monitorTransactions");
  }
  
  @Override
  public void monitorTransactionsTimeFrame(int numberOfTransactions) {
    LOGGER.info("monitorTransactionsTimeFrame method");
    cjfMeterService.transactionsInMethodTimeFrame(numberOfTransactions);
  }
}