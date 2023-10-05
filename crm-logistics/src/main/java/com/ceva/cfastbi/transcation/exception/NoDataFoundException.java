package com.ceva.cfastbi.transcation.exception;

public class NoDataFoundException extends RuntimeException {
  String message;

  public NoDataFoundException(String message) {
    super(message);
    this.message = message;
  }

}
