package com.ceva.cfastbi.transcation.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ceva.cfastbi.transcation.udt.dto.Customer;
import com.ceva.cfastbi.transcation.udt.dto.CustomerOrderDetails;
import com.ceva.cfastbi.transcation.udt.dto.OrderItems;
import com.ceva.cfastbi.transcation.udt.inbound.Addresses;

/**
 * Mapping inbound to dto.
 * 
 * @author shaik
 *
 */
public class CustomerOrderDetailsMapping {
  private static Logger logger = LoggerFactory.getLogger(CustomerOrderDetailsMapping.class);

  /**
   * Setting of inbound and dto class values.
   * 
   * @return CustomerOrderDetails
   */
  public static CustomerOrderDetails curtomerOrderDetailsMapping(com.ceva.cfastbi.transcation.udt.inbound.CustomerOrderDetails customerorderdetails) {
    logger.info("recieved mapping layer");

    com.ceva.cfastbi.transcation.udt.dto.Addresses addresses =
        new com.ceva.cfastbi.transcation.udt.dto.Addresses();
    String addressid = customerorderdetails.getAddresses().getAddressId();
    addresses.setAddressId(addressid);
    String street = customerorderdetails.getAddresses().getStreet();
    addresses.setStreet(street);
    String city = customerorderdetails.getAddresses().getCity();
    addresses.setCity(city);
    String state = customerorderdetails.getAddresses().getState();
    addresses.setState(state);
    String postalcode = customerorderdetails.getAddresses().getPostalCode();
    addresses.setPostalCode(postalcode);
    String country = customerorderdetails.getAddresses().getCountry();
    addresses.setCountry(country);
    CustomerOrderDetails customerorderdetails2 = new CustomerOrderDetails();
    customerorderdetails2.setAddresses(addresses);
    Customer customer = new Customer();
    String lastname = customerorderdetails.getCustomer().getLastName();
    customer.setLastName(lastname);
    String email = customerorderdetails.getCustomer().getEmail();
    customer.setEmail(email);
    String phone = customerorderdetails.getCustomer().getPhone();
    customer.setPhone(phone);
    customerorderdetails2.setCustomer(customer);
    OrderItems orderitems = new OrderItems();
    String orderitemid = customerorderdetails.getOrderitems().getOrderItemId();
    orderitems.setOrderItemId(orderitemid);
    String orderid = customerorderdetails.getOrderitems().getOrderId();
    orderitems.setOrderId(orderid);
    String productid = customerorderdetails.getOrderitems().getProductId();
    orderitems.setProductId(productid);
    String quantity = customerorderdetails.getOrderitems().getQuantity();
    orderitems.setQuantity(quantity);
    customerorderdetails2.setOrderitems(orderitems);
    customerorderdetails2.setCustomerId(customerorderdetails.getcustomerid());
    return customerorderdetails2;
  }
}
