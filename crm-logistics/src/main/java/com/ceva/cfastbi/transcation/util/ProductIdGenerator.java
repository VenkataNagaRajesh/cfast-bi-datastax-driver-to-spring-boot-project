package com.ceva.cfastbi.transcation.util;

import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductIdGenerator {
  private static final Logger logger = LoggerFactory.getLogger(ProductIdGenerator.class);
  private static AtomicLong counter = new AtomicLong(loadCounterValue());

  /**
   * generate product id.
   */
  public static String generateProductId() {
    long id = counter.incrementAndGet();
    saveCounterValue(id); // Save the updated counter value
    String generatedId = "CATG" + String.format("%04d", id);
    logger.info("Generated Product ID: {}", generatedId);
    return generatedId;
  }

  private static long loadCounterValue() {
    logger.info("Loading counter value.");
    return 0;
  }

  private static void saveCounterValue(long value) {
    logger.info("Saving counter value: {}", value);
  }
}

