package com.ceva.cfastbi.transcation.common;

import org.slf4j.Logger;
import org.slf4j.MDC;

public class CfastBiTranscationLoggerUtils {

  public static final String REQUEST_ID = "correlation-id";


  /**
   * Get the current request correlation id.
   * 
   * @return
   */
  public static String getCorrelationId() {

    return MDC.get(REQUEST_ID);

  }
  
  private static Object[] getArguments(Object... arguments) {

    int size = 1;
    if (arguments != null) {
      size = size + arguments.length;
    }
    Object[] mod = new Object[size];
    mod[0] = getCorrelationId();
    if (mod[0] == null) {
      mod[0] = "Unknown Transaction Id";
    }
    if (arguments != null) {
      for (int i = 0; i < arguments.length; i++) {
        mod[i + 1] = arguments[i];
      }
    }

    return mod;
  }

  private static String getFormat(String format) {

    return "[{}] -> " + format;
  }
  
  /**
   * Error formated message.
   * 
   * @param logger Logger
   * @param format Message
   * @param arguments Arguments
   */
  public static void error(Logger logger, String format, Object... arguments) {
    if (logger.isErrorEnabled()) {
      logger.error(getFormat(format), getArguments(arguments));
    }
  }

  /**
   * Error message with cause.
   * 
   * @param logger Logger
   * @param msg Message
   * @param th Exception
   */
  public static void error(Logger logger, String msg, Throwable th) {
    if (logger.isInfoEnabled()) {
      logger.error(msg, th);
    }
  }

  /**
   * Debug formated message.
   * 
   * @param logger Logger
   * @param format Message
   * @param arguments Arguments
   */
  public static void debug(Logger logger, String format, Object... arguments) {
    if (logger.isDebugEnabled()) {
      logger.debug(getFormat(format), getArguments(arguments));
    }
  }
  
  /**
   * Info formated message.
   * 
   * @param logger Logger
   * @param format Message
   * @param arguments Arguments
   */
  public static void info(Logger logger, String format, Object... arguments) {

    if (logger.isInfoEnabled()) {
      logger.info(getFormat(format), getArguments(arguments));
    }
  }



}
