package com.ceva.cfastbi.transcation.exception;

public class SaveRecordException extends Exception {

  private static final long serialVersionUID = 1L;

  private int errorCode;
  private String errorMsg;

  public SaveRecordException(Throwable t) {
    super(t);
  }

  public SaveRecordException(String message, Throwable t) {
    super(message, t);
  }

  /**
   * SaveRecordException.
   */
  public SaveRecordException(SaveRecordExceptionCodes code, Throwable t) {
    super(code.getMsg(), t);
    this.errorMsg = code.getMsg();
    this.errorCode = code.getId();
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

}


