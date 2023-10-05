package com.ceva.cfastbi.transcation.exception;

/**
 * UnifiedException.
 * 
 * @author Narayana
 *
 */
public class CfastBiTransactionException extends Exception {
  private static final long serialVersionUID = 1L;

  private String errorMessage;
  private String srcSystem;
  private String srcName;
  private String loglevel;
  private String tableName;

  /**
   * CustomException.
   * 
   * @param errorMessage String
   * @param srcName String
   * @param srcSystem String
   * @param tableName String
   * @param loglevel String
   */
  public CfastBiTransactionException(String errorMessage, String srcName, String srcSystem, String tableName,
      String loglevel) {
    super(errorMessage);
    this.errorMessage = errorMessage;
    this.srcName = srcName;
    this.srcSystem = srcSystem;
    this.tableName = tableName;
    this.loglevel = loglevel;
  }

  /**
   * CustomException.
   * 
   * @param errorMessage String
   * @param srcName String
   * @param loglevel String
   */
  public CfastBiTransactionException(String errorMessage, String srcName, String tableName,
      String loglevel) {
    super(errorMessage);
    this.errorMessage = errorMessage;
    this.srcName = srcName;
    this.tableName = tableName;
    this.loglevel = loglevel;
  }

  /**
   * getErrorMessage.
   * 
   * @return errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * setErrorMessage.
   * 
   * @param errorMessage String
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * getLoglevel.
   * 
   * @return loglevel
   */
  public String getLoglevel() {
    return loglevel;
  }

  /**
   * setLoglevel.
   * 
   * @param loglevel string
   */
  public void setLoglevel(String loglevel) {
    this.loglevel = loglevel;
  }

  public String getSrcSystem() {
    return srcSystem;
  }

  public void setSrcSystem(String srcSystem) {
    this.srcSystem = srcSystem;
  }

  public String getSrcName() {
    return srcName;
  }

  public void setSrcName(String srcName) {
    this.srcName = srcName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

}

