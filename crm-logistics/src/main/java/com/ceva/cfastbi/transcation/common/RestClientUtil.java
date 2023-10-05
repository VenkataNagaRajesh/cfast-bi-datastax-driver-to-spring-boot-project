package com.ceva.cfastbi.transcation.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to deal with rest calls.
 * 
 * @author nebotlc
 *
 */
public class RestClientUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestClientUtil.class);

  private static final String DEFAULT_CHARSET = "UTF-8";

  private String endPointUrl;
  private String user;
  private String password;
  private String bearerToken;

  private RestClientUtil(String endPointUrl, String user, String password, String bearerToken) {
    super();
    this.endPointUrl = endPointUrl;
    this.user = user;
    this.password = password;
    this.bearerToken = bearerToken;
  }

  /**
   * Get the instance of RestClientUtil with basic authentication.
   * 
   * @param endPointUrl URL
   * @param user User
   * @param password Password
   * @return The client
   */
  public static RestClientUtil getInstance(String endPointUrl, String user, String password) {
    return new RestClientUtil(endPointUrl, user, password, null);
  }

  /**
   * Get the instance of RestClientUtil with oauth authentication.
   * 
   * @param endPointUrl URL
   * @param bearerToken Token
   * @return The client
   */
  public static RestClientUtil getInstance(String endPointUrl, String bearerToken) {
    return new RestClientUtil(endPointUrl, null, null, bearerToken);
  }



  /**
   * Cal POST Service Invocation.
   * 
   * @param request request parameter
   */
  public Map.Entry<Integer, String> sendPostRequest(String request, String contentType,
      String charset) {

    Map.Entry<Integer, String> response = null;

    try {



      String targetCharset = charset;
      if (targetCharset == null) {
        targetCharset = DEFAULT_CHARSET;
      }

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Request: {}", request);
      }

      response = postRequest(request, contentType, targetCharset);
      // TODO replace with the below once is implemented:
      // response = postRequestWithRestTemplate(request, contentType, targetCharset);

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Response: {}", response);
      }

    } catch (Exception e) {
      LOGGER.error("Error Occured : ", e);
      return new AbstractMap.SimpleEntry<Integer, String>(-1, "Error in POST request");
    }
    return response;
  }

  /**
   * Cal GET Service Invocation.
   * 
   * 
   */
  public Map.Entry<Integer, String> sendGetRequest() {

    Map.Entry<Integer, String> response = null;

    try {


      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Get");
      }

      response = getRequest();
      // TODO replace with the below once is implemented:
      // response = getRequestWithRestTemplate()

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Response: {}", response.getValue());
      }

    } catch (Exception e) {
      LOGGER.error("Error Occured : ", e);
      return new AbstractMap.SimpleEntry<Integer, String>(-1, "Error in GET request");
    }
    return response;
  }


  /**
   * Post the json to webservice.
   *
   * @param request String jsonRequest
   * @return String response
   * @throws URISyntaxException The URISyntaxException
   * @throws IOException The IOException
   */
  private Map.Entry<Integer, String> postRequest(String request, String contentType, String charset)
      throws IOException, URISyntaxException {

    String responseStr = null;


    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

      URIBuilder uriBuilder = new URIBuilder(endPointUrl);


      HttpPost httpPost = new HttpPost(uriBuilder.build().toString());
      HttpEntity requestEntity = null;
      if (charset != null) {
        requestEntity = new StringEntity(request, ContentType.create(contentType, charset));
      } else {
        requestEntity = new StringEntity(request, ContentType.create(contentType));
      }
      httpPost.setEntity(requestEntity);
      if (user != null && password != null) {
        httpPost.setHeader("Authorization", getEncodedAuthorization(user, password));
      } else if (bearerToken != null) {
        httpPost.setHeader("Authorization", "Bearer " + bearerToken);
      }
      String correlationId = CfastBiTranscationLoggerUtils.getCorrelationId();
      if (correlationId != null) {
        httpPost.setHeader(CfastBiTranscationLoggerUtils.REQUEST_ID, correlationId);
      } 
      HttpResponse httpResponse = null;
      HttpEntity responseEntity = null;

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("###### Sending request ...");
      }
      long startTime = System.currentTimeMillis();
      httpResponse = httpClient.execute(httpPost);
      long stopTime = System.currentTimeMillis();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Time took to execute the webservice: {}", (stopTime - startTime));
      }
      if (httpResponse.getStatusLine().getStatusCode() != 200) {
        responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
          String result = EntityUtils.toString(responseEntity, charset);
          String errorDetailsString = httpResponse.getStatusLine()
              + ((responseEntity.getContentType() == null) ? "_"
                  : responseEntity.getContentType().getValue())
              + " (" + responseEntity.getContentLength() + " bytes)" + result + request;

          LOGGER.error("Received non-200 response. {}", errorDetailsString);

          return new AbstractMap.SimpleEntry<Integer, String>(1, errorDetailsString);

        } else {
          throw new IOException("Received non-200 response. " + httpResponse.getStatusLine()
              + httpPost.getURI() + ".");
        }
      } else {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Response SUCCESSFUL!");
        }
      }
      responseEntity = httpResponse.getEntity();
      if (responseEntity == null) {
        throw new IOException("No response from " + httpPost.getURI() + ".");
      }
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("###### Response received: "
            + ((responseEntity.getContentType() == null) ? "_"
                : responseEntity.getContentType().getValue())
            + " (" + responseEntity.getContentLength() + " bytes)");
      }
      responseStr = EntityUtils.toString(responseEntity, charset);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Response: {}", responseStr);
      }
    }

    return new AbstractMap.SimpleEntry<Integer, String>(0, responseStr);
  }


  /**
   * Get the json to webservice.
   *
   * @param request String jsonRequest
   * @return String response
   * @throws URISyntaxException The URISyntaxException
   * @throws IOException The IOException
   */
  private Map.Entry<Integer, String> getRequest() throws IOException, URISyntaxException {

    String responseStr = null;


    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

      URIBuilder uriBuilder = new URIBuilder(endPointUrl);

      HttpGet httpGet = new HttpGet(uriBuilder.build().toString());
      if (user != null && password != null) {
        httpGet.setHeader("Authorization", getEncodedAuthorization(user, password));
      } else if (bearerToken != null) {
        httpGet.setHeader("Authorization", "Bearer " + bearerToken);
      }
      String correlationId = CfastBiTranscationLoggerUtils.getCorrelationId();
      if (correlationId != null) {
        httpGet.setHeader(CfastBiTranscationLoggerUtils.REQUEST_ID, correlationId);
      }
      HttpResponse httpResponse = null;
      HttpEntity responseEntity = null;

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("###### Sending request ...");
      }
      long startTime = System.currentTimeMillis();
      httpResponse = httpClient.execute(httpGet);
      long stopTime = System.currentTimeMillis();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Time took to execute the webservice: {}", (stopTime - startTime));
      }
      if (httpResponse.getStatusLine().getStatusCode() != 200) {
        responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
          String result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
          String errorDetailsString = httpResponse.getStatusLine()
              + ((responseEntity.getContentType() == null) ? "_"
                  : responseEntity.getContentType().getValue())
              + " (" + responseEntity.getContentLength() + " bytes)" + result;

          LOGGER.error("Received non-200 response. {}", errorDetailsString);

          return new AbstractMap.SimpleEntry<Integer, String>(1, errorDetailsString);
        } else if (httpResponse.getStatusLine().getStatusCode() == 204) {
          return new AbstractMap.SimpleEntry<Integer, String>(1, Constant.NO_RESULTS_FOUND);
        } else {
          throw new IOException("Received non-200 and 204 response. " + httpResponse.getStatusLine()
              + httpGet.getURI() + ".");
        }
      } else {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Response SUCCESSFUL!");
        }
      }
      responseEntity = httpResponse.getEntity();
      if (responseEntity == null) {
        throw new IOException("No response from " + httpGet.getURI() + ".");
      }
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("###### Response received: "
            + ((responseEntity.getContentType() == null) ? "_"
                : responseEntity.getContentType().getValue())
            + " (" + responseEntity.getContentLength() + " bytes)");
      }
      responseStr = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Response: {}", responseStr);
      }
    }
    return new AbstractMap.SimpleEntry<Integer, String>(0, responseStr);
  }

  /**
   * Get an Authorization token for a HTTP header.
   * 
   * @param username user name
   * @param password pasword
   * @return The encoded token
   */
  public static String getEncodedAuthorization(String username, String password) {
    if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
      String userPassword = username + ":" + password;
      String headerValue = "Basic " + Base64.getEncoder().encodeToString(userPassword.getBytes());
      return headerValue;
    }
    return "";
  }


}
