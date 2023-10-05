package com.ceva.cfastbi.transcation.exception;

import org.springframework.http.HttpStatus;

/**
 * Error response class.
 * 
 * @author shaik
 *
 */
public class NoDataFoundError {
  @Override
  public String toString() {
    return "NoDataFoundError [description=" + description + ", code=" + code + "]";
  }

  private String description;
  private HttpStatus code;

  public NoDataFoundError() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * constructor.
   */
  public NoDataFoundError(String description, HttpStatus code) {
    super();
    this.description = description;
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public HttpStatus getCode() {
    return code;
  }

  public void setCode(HttpStatus code) {
    this.code = code;
  }

}
