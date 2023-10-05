/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.actuator.CjfMeterService;
import com.ceva.cfastbi.transcation.service.PrometheusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The goal of this Rest controller is to define and test the prometheus integration with CJF rest applications.
 * 
 * @author Raul Garnacho
 */
@RestController
@RequestMapping({"/prometheus_testing"})
@Tag(name = "Prometheus", description = "The Prometheus API")
public class PrometheusRestController {
  
  @Autowired
  private CjfMeterService cjfMeterService;
  
  @Autowired
  private PrometheusService prometheusService;

  /**
   * Method to test the metric for how many failures occurred in a service.
   * @param error if the value is true and exception is thrown.
   * @return String with the response.
   */
  @GetMapping({"/error/in/service1/{error}"})
  @Operation(summary = "Error In Service1",
          description = "This handles Error In Service1 in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String errorInService1(@PathVariable boolean error) {
    try {
      if (error) {
        throw new Exception();
      }
    } catch (Exception e) {
      cjfMeterService.errorInService("/error/in/service1", e);
    }
    return "errorInService1 response";
  }
  
  /**
   * Method to test the metric for how many failures occurred in a service.
   * @param error if the value is true and exception is thrown.
   * @return String with the response.
   */
  @GetMapping({"/error/in/service2/{error}"})
  @Operation(summary = "Error In Service2",
          description = "This handles Error In Service2 in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String errorInService2(@PathVariable boolean error) {
    try {
      if (error) {
        throw new Exception();
      }
    } catch (Exception e) {
      cjfMeterService.errorInService("/error/in/service2", e);
    }
    return "errorInService2 response";
  }
  
  /**
   * Method to simulate the number of transactions that a service method is invoked.
   * @param numberOfTransactions Number of times the method will be invoked.
   * @return String with the response.
   */
  @GetMapping({"/monitor_transactions/{numberOfTransactions}"})
  @Operation(summary = "Monitor Transactions",
          description = "This handles monitorTransactions in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String monitorTransactions(@PathVariable int numberOfTransactions) {
    for (int i = 1; i <= numberOfTransactions; i++) {
      prometheusService.monitorTransactions();
    }
    return "monitorTransactions response";
  }
  
  /**
   * Method to simulate the number of transactions that a service method is invoked during a time frame.
   * @param numberOfTransactions Number of times the method will be invoked.
   * @return String with the response.
   */
  @GetMapping({"/monitor_transactions_time_frame/{numberOfTransactions}"})
  @Operation(summary = "Monitor Transactions TimeFrame",
          description = "This handles monitorTransactionsTimeFrame in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String monitorTransactionsTimeFrame(@PathVariable int numberOfTransactions) {
    prometheusService.monitorTransactionsTimeFrame(numberOfTransactions);
    return "monitorTransactions response";
  }
}