/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.service;

/**
 * The goal of this service is to define and test the prometheus integration with CJF rest applications.
 * 
 * @author Raul Garnacho
 */
public interface PrometheusService {
  
  /**
   * Method to monitor the number of transactions running.
   */
  void monitorTransactions();
  
  /**
   * Method to monitor the number of transactions running in a time frame.
   * @param numberOfTransactions Number of transactions.
   */
  void monitorTransactionsTimeFrame(int numberOfTransactions);
}