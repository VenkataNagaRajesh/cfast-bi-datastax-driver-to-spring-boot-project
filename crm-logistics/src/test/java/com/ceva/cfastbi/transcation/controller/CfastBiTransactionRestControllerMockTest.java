package com.ceva.cfastbi.transcation.controller;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * CfastBiTransactionRestControllerMockTest.
 * 
 * @author narayana
 *
 */

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CfastBiTransactionRestControllerMockTest {
  /*
   * 
   * 
   * @Mock Authentication authentication;
   * 
   * @Mock SecurityContext securityContext;
   * 
   * @InjectMocks private BrokerageRestController brokerageRestController; private String jobNumber;
   * 
   *//**
      * It will run before the current class.
      */
  /*
   * @BeforeAll public void setupForUserList() { jobNumber = "DAC199010785"; }
   * 
   *//**
      * It will run before each Test Case.
      */
  /*
   * @BeforeEach public void setup() {
   * 
   * doReturn(authentication).when(securityContext).getAuthentication();
   * SecurityContextHolder.setContext(securityContext);
   * doReturn(userList).when(brokerageService).getAllUsers(); mockMvc =
   * MockMvcBuilders.standaloneSetup(brokerageRestController) .setControllerAdvice(new
   * GlobalExceptionHandler()).build();
   * 
   * }
   * 
   * @Test
   * 
   * @Tag(TestTags.PURE_UNIT)
   * 
   * @DisplayName("POST booking - Success Data") public void testBookingUsersSuccess() {
   * 
   * ResponseEntity<ResponseMessage> responseEntity; try { responseEntity =
   * brokerageRestController.writeBrokerageData(jobNumber, "BOOKING");
   * 
   * 
   * verify(brokerageService, times(1)).processBrokerageServiceInbound(jobNumber, "BOOKING", new
   * Date());
   * 
   * Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value(),
   * "Post request was not success"); //
   * "Brokerage Service is ended to process : BOOKING Job number : " + jobNumber,
   * 
   * } catch (Exception e) { e.printStackTrace(); } }
   * 
   * 
   * @Test
   * 
   * @Tag(TestTags.PURE_UNIT)
   * 
   * @DisplayName("POST booking - Success Status Code") public void
   * testPostBookingSuccessStatusCode() throws Exception {
   * 
   * MockHttpServletResponse response =
   * mockMvc.perform(post("/cfast-bi-transaction-batch-split").andReturn() .getResponse();
   * verify(brokerageService, times(1)).processBrokerageServiceInbound(jobNumber, "BOOKING", new
   * Date()); Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus(),
   * "GET request failed");
   * 
   * }
   * 
   * 
   */
}
