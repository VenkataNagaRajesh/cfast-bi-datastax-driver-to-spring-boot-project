package com.ceva.cfastbi.transcation.exception;



public class InvalidInputFormat extends RuntimeException {
  String message;

  public InvalidInputFormat(String message) {
    super(message);
    this.message = message;
  }

}
