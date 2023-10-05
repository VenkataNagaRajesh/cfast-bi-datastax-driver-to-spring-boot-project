package com.ceva.cfastbi.transcation.udt.inbound;

/**
 * Products class.
 * 
 * @author shaik
 *
 */

public class Products {
  private String productId;
  private String productrName;
  private String productDescription;
  private String price;

  /**
   * Constructor.
   */
  public Products(String productId, String productrName, String productDescription, String price) {
    super();
    this.productId = productId;
    this.productrName = productrName;
    this.productDescription = productDescription;
    this.price = price;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductrName() {
    return productrName;
  }

  public void setProductrName(String productrName) {
    this.productrName = productrName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Products [productId=" + productId + ", productrName=" + productrName
        + ", productDescription=" + productDescription + ", price=" + price + "]";
  }

}
