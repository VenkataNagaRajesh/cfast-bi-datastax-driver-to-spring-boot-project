/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.controller.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.actuator.CjfMeterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The goal of this Rest controller is to define and test the actuator integration with CJF rest applications.
 * 
 * @author Raul Garnacho
 */
@RestController
@RequestMapping({"/cjf_actuator_testing"})
@Tag(name = "Actuator", description = "The Actuator API")
public class ActuatorRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActuatorRestController.class);
  
  @Autowired
  private CjfMeterService cjfMeterService;

  /**
   * Shows messages in different log levels.
   * @return String with the response.
   */
  @GetMapping({"/logger"})
  @Operation(summary = "Logger",
          description = "This handles logger in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String logger() {
    LOGGER.trace("TRACE message");
    LOGGER.debug("DEBUG message");
    LOGGER.info("INFO message");
    LOGGER.warn("WARN message");
    LOGGER.error("ERROR message");

    return "logger response";
  }

  /**
   * Tests the actuator exception handling.
   * @return String with the response.
   */
  @GetMapping({"/exception_handling"})
  @Operation(summary = "Exception Handling",
          description = "This handles exceptions in the Application")
  @ResponseStatus(HttpStatus.OK)
  public String exceptionHandling() {
    LOGGER.info("In getUsersException");
    try {
      throw new Exception();
    } catch (Exception e) {
      cjfMeterService.newException(e);
    }
    return "exceptionHandling response";
  }
}