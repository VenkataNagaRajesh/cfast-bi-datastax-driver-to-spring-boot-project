package com.ceva.cfastbi.transcation.exception;

import java.util.Date;

public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;

  /**
   * Error Details.
   * @param timestamp timeStamp.
   * @param message message.
   * @param details details.
   */
  public ErrorDetails(Date timestamp, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }
  
  /**
   * Error Details to get the getTimestamp.
   * 
   */
  public Date getTimestamp() {
    return timestamp;
  }
  
  /**
   * Error Details to get the getMessage.
   * 
   */

  public String getMessage() {
    return message;
  }
  
  /**
   * Error Details to get the getDetails.
   * 
   */

  public String getDetails() {
    return details;
  }
}
