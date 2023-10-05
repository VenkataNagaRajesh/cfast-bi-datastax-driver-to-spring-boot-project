package com.ceva.cfastbi.transcation.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateConversionUtil.
 * 
 * @author Laxminarayana
 *
 */
public final class DateConversionUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(DateConversionUtil.class);

  private static final String[] DATE_FORMATS =
      new String[] {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-dd-MM HH:mm:ss",
          "yyyy:MM:dd'T'HH:mm:ssZ", "yyyy:MM:dd'T'HH:mm:ss'Z'", "dd-MM-yyyy HH:mm:ss",
          "yyyy-MM-dd'T'HH:mm:ss zzz", "E MMM dd HH:mm:ss Z yyyy"};

  public static final String FMT_YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";

  /**
   * date to string object with specific format and timezone.
   * 
   * @param date Date
   * @param inputTimezone String
   * @return
   */
  public static String formateWithZone(Date date, String inputTimezone) {
    SimpleDateFormat isoFormatter =
        new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT_FOR_TIMESTAMP_ZONE);
    if (StringUtils.isNotBlank(inputTimezone)) {
      isoFormatter.setTimeZone(TimeZone.getTimeZone(inputTimezone));
    } else {
      isoFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    return isoFormatter.format(date);
  }

  /**
   * Method to get a DatePattern based on pattern.
   * 
   * @throws ParseException exception
   */
  public static Date getDateWithStringTimezone(String inputDate, String inputTimezone) {
    try {
      if (StringUtils.isBlank(inputDate)) {
        return null;
      }
      String dateFormat = getDateFormat(inputDate);
      DateFormat formatter = new SimpleDateFormat(dateFormat);
      if (StringUtils.isNotBlank(inputTimezone)) {
        formatter.setTimeZone(TimeZone.getTimeZone(inputTimezone));
      } else {
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      }
      return formatter.parse(inputDate);
    } catch (ParseException e) {
      LOGGER.error("Exception while getDateWithStringTimezone {}", e.getMessage());
      return null;
    }
  }

  /**
   * getDateFormat.
   * 
   * @param text text
   * @return
   */
  public static String getDateFormat(final String text) {
    for (String dateFormat : DATE_FORMATS) {
      try {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        formatter.parse(text);
        return dateFormat;
      } catch (ParseException e) {
        // LOGGER.error("No Date parser found for given date:" + text + " dateFormat: " + dateFormat
        // + " ,Exception:" + e.getMessage());
        continue;
      }
    }
    return null;
  }

  /**
   * getDateAsString.
   * 
   * @param date Input Date to be formatted per DateFormat
   * @param dateFormat Date Format we want to convert it to, this needs to be a proper format or
   * @param timeZone default time zone will be UTC.
   * @return String formatted per SimpleDateFormat
   */
  public static String getDateAsString(Date date, String dateFormat, String timeZone) {
    DateFormat dateFormat2 = new SimpleDateFormat(dateFormat);
    if (Objects.isNull(timeZone) || timeZone.isEmpty()) {
      timeZone = Constant.UTC_TIME_ZONE_CODE;
    }
    dateFormat2.setTimeZone(TimeZone.getTimeZone(timeZone));
    return getDateAsString(date, dateFormat2);
  }

  /**
   * getDateAsString.
   * 
   * @param date Input Date to be formatted per DateFormat
   * @param dateFormat Date Format we want to convert it to
   * @return String formatted per SimpleDateFormat
   */
  public static String getDateAsString(Date date, DateFormat dateFormat) {
    return dateFormat.format(date);
  }

  /**
   * getDateFromString.
   * 
   * @param date date
   * @return
   */
  public static Date getDateFromString(String date) {
    if (date != null) {
      try {
        return new SimpleDateFormat(FMT_YYYY_MM_DD_HH_MM_SS_Z).parse(date);
      } catch (ParseException e) {
        return null;
      }
    }
    return null;
  }

  /**
   * getStringDateTimeZone.
   * 
   * @param date String
   * @param timeZone String
   * @return
   */
  public static String getStringDateTimeZone(String date, String timeZone) {
    try {
      if (StringUtils.isBlank(date)) {
        return null;
      }
      String dateFormat = getDateFormat(date);
      DateFormat formatter = new SimpleDateFormat(dateFormat);
      Instant instant = formatter.parse(date).toInstant();
      String date1 = null;
      if (StringUtils.isNotBlank(timeZone)) {
        date1 = formateWithZone(Date.from(instant), timeZone);
      } else {
        date1 = formateWithZone(Date.from(instant), Constant.UTC_TIME_ZONE_CODE);
      }
      return date1;
    } catch (ParseException e) {
      return null;
      // e.printStackTrace();
    }
  }
}
