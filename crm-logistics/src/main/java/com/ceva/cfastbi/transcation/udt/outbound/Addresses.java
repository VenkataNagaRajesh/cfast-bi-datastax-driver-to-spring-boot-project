package com.ceva.cfastbi.transcation.udt.outbound;

import com.datastax.driver.mapping.annotations.UDT;

/**
 * Addresses class.
 * 
 * @author shaik
 *
 */
@UDT(name = "addresses")
public class Addresses {
  private String addressId;
  private String street;
  private String city;
  private String state;
  private String postalCode;
  private String country;


  public Addresses() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * constructor.
   */
  public Addresses(String addressId, String customerId, String street, String city, String state,
      String postalCode, String country) {
    super();
    this.addressId = addressId;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
  }

  public String getAddressId() {
    return addressId;
  }

  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Addresses [addressId=" + addressId + ", street=" + street + ", city=" + city
        + ", state=" + state + ", postalCode=" + postalCode + ", country=" + country + "]";
  }
}
