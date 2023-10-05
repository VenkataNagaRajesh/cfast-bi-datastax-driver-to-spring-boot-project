package com.ceva.cfastbi.transcation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppStartupRunner.class);

  @Autowired
  PreLoadedJsonValues preloadJsonValues;

  @Override
  public void run(ApplicationArguments args) {
    LOGGER.info("Started call to MDM service for preload json values");
    LOGGER.info("Ended call to MDM service for preload json values");
  }

}