/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.actuator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
//import io.micrometer.core.instrument.util.StringUtils;

/**
 *  Check the status of the services consumed.
 * 
 * @author Raul Garnacho
 */
public class ServiceStatusMetrics implements MeterBinder {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceStatusMetrics.class);

  private static final String NAME = "cjf_services_to_monitor";
  private static final String DESCRIPTION = "Services to monitor status";
  private static final double UP = 1.0;
  private static final double DOWN = 0.0;
  private Iterable<Tag> tags;
  private final String servicesToMonitor;
  private double generalStatus;

  /**
   * Constructor.
   * 
   * @param servicesToMonitor Services to monitor.
   */
  public ServiceStatusMetrics(String servicesToMonitor) {
    this.servicesToMonitor = servicesToMonitor;
    this.tags = Tags.of(Tag.of("url", servicesToMonitor));
  }

  @Override
  public void bindTo(final MeterRegistry meterRegistry) {
    getStatusPerService();
    io.micrometer.core.instrument.Gauge.builder(NAME, this, x -> x.getStatus()).description(DESCRIPTION)
      .tags(tags).baseUnit("status").register(meterRegistry);
  }

  /**
   * Get the status of each service and add it as tags to the metric.
   */
  private void getStatusPerService() {
    this.generalStatus = UP;
    this.tags = null;
    if (! StringUtils.isNotBlank(servicesToMonitor)) {
      var serviceUrlList = Arrays.asList(servicesToMonitor.split(","));
      for (String serviceUrl : serviceUrlList) {
        URL url;
        int responseCode = 0;
        try {
          url = new URL(serviceUrl);
          var huc = (HttpURLConnection) url.openConnection();
          responseCode = huc.getResponseCode();
        } catch (IOException e) {
          LOGGER.error("Error checking the status of the service: {}", serviceUrl, e);
        }
        boolean serviceStatusOk = (HttpURLConnection.HTTP_OK == responseCode);
        if (!serviceStatusOk) {
          this.generalStatus = DOWN;
        }
        this.tags = Tags.concat(tags, Tags.of(Tag.of(serviceUrl, serviceStatusOk ? "UP" : "DOWN")));
      }
    }
  }

  private double getStatus() {
    return this.generalStatus;
  }
}