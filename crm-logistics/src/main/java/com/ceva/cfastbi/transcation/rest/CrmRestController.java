package com.ceva.cfastbi.transcation.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.common.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * CfastBiTransactionRestController. IProcess transcation batch to individual records
 * 
 * @author Laxminarayana
 *
 */
@RestController
@RequestMapping(value = "/crm-logistics")
public class CrmRestController {


  /**
   * writeUnifiedData.
   * 
   * @param request String
   * @return
   */
  @PostMapping(value = "")
  @ApiOperation(value = "Process transcation batch to individual records",
      notes = "Process transcation batch to individual records")
  public ResponseEntity<ResponseMessage> splitTranscationBatch(
      @ApiParam(value = "Provide mentioned transaction batch ",
          required = true) @RequestBody String request) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseMessage("Application is not ready to proceed"));
  }



}


