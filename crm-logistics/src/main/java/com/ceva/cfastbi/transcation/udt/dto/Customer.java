package com.ceva.cfastbi.transcation.udt.dto;

import com.datastax.driver.mapping.annotations.UDT;

/**
 * customer class.
 * 
 * @author shaik
 *
 */
@UDT(name = "customer")
public class Customer {
  private String lastName;
  private String email;
  private String phone;


  public Customer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * constructor.
   */
  public Customer(String lastName, String email, String phone) {
    super();
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }

  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getPhone() {
    return phone;
  }


  public void setPhone(String phone) {
    this.phone = phone;
  }


  @Override
  public String toString() {
    return "Customer [lastName=" + lastName + ", email=" + email + ", phone=" + phone + "]";
  }


}
