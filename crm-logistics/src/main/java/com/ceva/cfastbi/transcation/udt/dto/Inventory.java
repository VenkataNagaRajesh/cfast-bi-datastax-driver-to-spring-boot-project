package com.ceva.cfastbi.transcation.udt.dto;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = "inventory")
public class Inventory {
  @PartitionKey
  @Column(name = "product_id")
  private String productId;
  @Column(name = "product_name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private Double price;
  @Column(name = "quantity")
  private Integer quantity;
  @Column(name = "category")
  private String category;

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
