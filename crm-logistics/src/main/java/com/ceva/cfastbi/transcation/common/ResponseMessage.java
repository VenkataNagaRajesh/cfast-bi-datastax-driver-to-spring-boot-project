package com.ceva.cfastbi.transcation.common;

/**
 * ResponseMessage.
 * 
 * @author Laxminarayana
 *
 */
public class ResponseMessage {
  private String message;

  /**
   * ResponseMessage.
   * 
   * @param message String
   */
  public ResponseMessage(String message) {
    this.message = message;
  }

  /**
   * getMessage.
   * 
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * setMessage.
   * 
   * @param message String
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
