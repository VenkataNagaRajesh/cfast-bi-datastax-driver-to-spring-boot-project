/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Custom health indicator for actuator.
 * 
 * @author Raul Garnacho
 */
@Component
public class CjfHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    return Health.up().withDetail("Detail of a custom health indicator", "OK").build();
  }
}