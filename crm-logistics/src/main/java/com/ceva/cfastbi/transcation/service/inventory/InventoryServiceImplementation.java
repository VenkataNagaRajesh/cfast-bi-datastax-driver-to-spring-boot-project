package com.ceva.cfastbi.transcation.service.inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceva.cfastbi.transcation.repository.InventoryRepository;
import com.ceva.cfastbi.transcation.udt.dto.Inventory;
import com.ceva.cfastbi.transcation.udt.inbound.InventoryInbound;
import com.ceva.cfastbi.transcation.util.ProductIdGenerator;

@Service
public class InventoryServiceImplementation implements InventoryService {
  private static final Logger logger =
      LoggerFactory.getLogger(InventoryServiceImplementation.class);
  @Autowired
  InventoryRepository inventoryRepository;

  @Override
  public Inventory saveProduct(InventoryInbound inv) {
    logger.info("Saving product: {}", inv.getName());
    // TODO Auto-generated method stub
    Inventory i = new Inventory();
    i.setProductId(ProductIdGenerator.generateProductId());
    i.setName(inv.getName());
    i.setDescription(inv.getDescription());
    i.setPrice(inv.getPrice());
    i.setQuantity(inv.getQuantity());
    i.setCategory(inv.getCategory());
    Inventory savedProduct = inventoryRepository.saveProduct(i);

    logger.info("Saved product with ID: {}", savedProduct.getProductId());
    return i;
  }

  @Override
  public List<Inventory> getAllProducts() {
    logger.info("Retrieving all products from the database.");
    return inventoryRepository.getAllProducts();
  }

  @Override
  public Inventory getProductById(String productId) {
    logger.info("Retrieving product with ID: {}", productId);
    return inventoryRepository.getProductById(productId);
  }

  @Override
  public Inventory updateProduct(String productId, InventoryInbound inv) {
    Inventory existingProduct = getProductById(productId);

    if (existingProduct == null) {
      logger.warn("Product with ID {} not found.", productId);
      return null;
    }

    existingProduct.setName(inv.getName());
    existingProduct.setDescription(inv.getDescription());
    existingProduct.setPrice(inv.getPrice());
    existingProduct.setQuantity(inv.getQuantity());
    existingProduct.setCategory(inv.getCategory());

    Inventory updatedProduct = inventoryRepository.updateProduct(existingProduct);

    logger.info("Updated product with ID: {}", updatedProduct.getProductId());
    return updatedProduct;
  }

  @Override
  public boolean deleteProduct(String productId) {
    Inventory existingProduct = getProductById(productId);

    if (existingProduct == null) {
      logger.warn("Product with ID {} not found for deletion.", productId);
      return false;
    }

    inventoryRepository.deleteProduct(existingProduct);
    logger.info("Deleted product with ID: {}", productId);
    return true;
  }

  @Override
  public Inventory updateProductQuantity(String productId, Integer newQuantity) {
    Inventory existingProduct = getProductById(productId);

    if (existingProduct == null) {
      logger.warn("Product with ID {} not found for updating quantity.", productId);
      return null;
    }

    existingProduct.setQuantity(newQuantity);
    Inventory updatedProduct = inventoryRepository.updateProduct(existingProduct);

    logger.info("Updated quantity of product with ID: {}", productId);
    return updatedProduct;
  }

}

