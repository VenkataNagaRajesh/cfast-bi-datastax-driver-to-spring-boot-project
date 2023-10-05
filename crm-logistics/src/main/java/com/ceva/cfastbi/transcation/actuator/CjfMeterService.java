/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.actuator;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.core.instrument.util.StringUtils;
import io.prometheus.client.Gauge;

/**
 * Example of Meter service to customized new Actuator and Prometheus metrics.
 * 
 * @author Raul Garnacho
 */
@Component
public class CjfMeterService {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(CjfMeterService.class);
  private static final String CJF_SERVICE_EXCEPTIONS = "cjf.service.exceptions";
  private static final String CJF_MONITOR_TRANSACTIONS = "cjf.monitor.transactions";
  private final MeterRegistry meterRegistry;
  private Counter exceptionCounter;
  private Map<String, Counter> errorsInServiceCounterMap = new HashMap<String, Counter>();
  private Map<String, Counter> transactionsInMethodCounterMap = new HashMap<String, Counter>();
  private Gauge transactionsInTimeFrameGauge;
  //@Value("${cjf.monitor.services}")
  private String servicesToMonitor;


  /**
   * Contructor.
   * @param meterRegistry Meter registry.
   * 
   */
  
  public CjfMeterService(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    this.exceptionCounter = this.meterRegistry.counter("cjf.exceptions");
  }

  /**
   * TransactionTimeFrameMetrics bean.
   * @return TransactionTimeFrameMetrics instance.
   */
  @Bean
  TransactionTimeFrameMetrics transactionTimeFrameMetrics() {
    transactionsInTimeFrameGauge = new Gauge.Builder().name("TMP_NAME").help("TMP_HELP").create();
    var timeFrameMetrics = new TransactionTimeFrameMetrics(transactionsInTimeFrameGauge);
    return timeFrameMetrics;
  }
  
  /**
   * ServiceStatusMetrics bean.
   * @return ServiceStatusMetrics instance.
   */
  @Bean
  ServiceStatusMetrics serviceStatusMetrics() {
    LOGGER.info("Services to monitor: {}", servicesToMonitor);
    if (StringUtils.isEmpty(servicesToMonitor)) {
      return null;
    }
    var serviceStatus = new ServiceStatusMetrics(servicesToMonitor);
    return serviceStatus;
  }

  /**
   * Manages the new exceptions.
   * @param e Exception thrown.
   */
  public void newException(Exception e) {
    LOGGER.error("New Exception: {}", e);
    exceptionCounter.increment(1.0);
  }

  /**
   * Manages the errors in services.
   * @param serviceName Service name.
   * @param e Exception thrown.
   */
  public void errorInService(String serviceName, Exception e) {
    LOGGER.error("New Exception: {}", e);
    var key = CJF_SERVICE_EXCEPTIONS + serviceName;
    if (errorsInServiceCounterMap.containsKey(key)) {
      var counter = errorsInServiceCounterMap.get(key);
      counter.increment(1.0);
    } else {
      var counter = this.meterRegistry.counter(key);
      counter.increment(1.0);
      errorsInServiceCounterMap.put(key, counter);
    }
  }
  
  /**
   * Manages the transactions in methods.
   * @param methodName Method name.
   */
  public void transactionsInMethod(String methodName) {
    LOGGER.info("New transaction: {}", methodName);
    var key = CJF_MONITOR_TRANSACTIONS + methodName;
    if (transactionsInMethodCounterMap.containsKey(key)) {
      var counter = transactionsInMethodCounterMap.get(key);
      counter.increment(1.0);
    } else {
      var counter = this.meterRegistry.counter(key);
      counter.increment(1.0);
      transactionsInMethodCounterMap.put(key, counter);
    }
  }
  
  /**
   * Manages the transactions in methods during a time frame.
   */
  public void transactionsInMethodTimeFrame(int transactions) {
    transactionsInTimeFrameGauge.inc(transactions);
  }
}