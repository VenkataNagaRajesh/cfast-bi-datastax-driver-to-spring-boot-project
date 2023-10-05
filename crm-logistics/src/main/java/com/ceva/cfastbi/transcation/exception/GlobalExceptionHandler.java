package com.ceva.cfastbi.transcation.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   * resource exception handler.
   * 
   * @param ex exception.
   * @param request request.
   * @return response.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * exception Handler.
   * 
   * @param ex exception.
   * @param request request.
   * @return response.
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * resource unauthorized exception handler.
   * 
   * @param ex exception.
   * @param request request.
   * @return response.
   */
  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  public ResponseEntity<?> resourceNotFoundExceptionUnauthorized(UnauthorizedException ex,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * resource forbidden exception handler.
   * 
   * @param ex exception.
   * @param request request.
   * @return response.
   */
  @ExceptionHandler(ResourceForbiddenException.class)
  @ResponseStatus(code = HttpStatus.FORBIDDEN)
  public ResponseEntity<?> resourceNotFoundExceptionForbidden(ResourceForbiddenException ex,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidInputFormat.class)
  public ResponseEntity<String> inValid(InvalidInputFormat invalidinputformat) {
    return new ResponseEntity<String>(invalidinputformat.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoDataFoundException.class)
  @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
  public ResponseEntity<Object> noData(NoDataFoundException nodatafound) {
    NoDataFoundError error = new NoDataFoundError(nodatafound.getMessage(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
  }
}
