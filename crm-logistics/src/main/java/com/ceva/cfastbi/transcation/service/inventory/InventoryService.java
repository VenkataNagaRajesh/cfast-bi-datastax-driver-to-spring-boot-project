package com.ceva.cfastbi.transcation.service.inventory;

import java.util.List;

import com.ceva.cfastbi.transcation.udt.dto.Inventory;
import com.ceva.cfastbi.transcation.udt.inbound.InventoryInbound;

public interface InventoryService {
  public Inventory saveProduct(InventoryInbound inv);

  List<Inventory> getAllProducts();

  Inventory getProductById(String productId);

  Inventory updateProduct(String productId, InventoryInbound inv);

  boolean deleteProduct(String productId);

  Inventory updateProductQuantity(String productId, Integer newQuantity);
}
