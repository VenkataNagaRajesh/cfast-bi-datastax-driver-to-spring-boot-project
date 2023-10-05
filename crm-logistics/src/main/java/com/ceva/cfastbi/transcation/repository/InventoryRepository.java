package com.ceva.cfastbi.transcation.repository;

import java.util.List;

import com.ceva.cfastbi.transcation.udt.dto.Inventory;

public interface InventoryRepository {
  public Inventory saveProduct(Inventory inv);

  List<Inventory> getAllProducts();

  public Inventory getProductById(String productId);

  Inventory updateProduct(Inventory inv);

  void deleteProduct(Inventory inv);
}
