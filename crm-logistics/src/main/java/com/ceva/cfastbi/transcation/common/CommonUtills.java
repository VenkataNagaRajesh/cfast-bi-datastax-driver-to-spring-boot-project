package com.ceva.cfastbi.transcation.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;

/**
 * CommonUtills.
 */
public class CommonUtills {

  /**
   * Check json or not.
   */
  public static Boolean isJsonOrNot(String json) {
    try {
      new ObjectMapper().readTree(json);
      return true;
    } catch (JsonProcessingException e) {
      return false;
      // TODO: handle exception
    }
  }
}
