package com.ceva.cfastbi.transcation.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ceva.cfastbi.transcation.service.inventory.InventoryService;
import com.ceva.cfastbi.transcation.udt.dto.Inventory;
import com.ceva.cfastbi.transcation.udt.inbound.InventoryInbound;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryRestController {
  private static final Logger logger = LoggerFactory.getLogger(InventoryRestController.class);
  @Autowired
  InventoryService inventoryService;

  /**
   * save product details.
   */
  @PostMapping(value = "/saveproduct")
  public Inventory saveProduct(@RequestBody InventoryInbound i) {
    logger.info("Received request to save product: {}", i.getName());

    Inventory savedProduct = inventoryService.saveProduct(i);
    logger.info("Saved product with ID: {}", savedProduct.getProductId());
    return savedProduct;
  }

  @GetMapping(value = "/getallproducts")
  public List<Inventory> getAllProducts() {
    logger.info("Received request to retrieve all products.");
    return inventoryService.getAllProducts();
  }

  /**
   * get product details based on id.
   */
  @GetMapping(value = "/getproduct/{productId}")
  public ResponseEntity<Inventory> getProductById(@PathVariable String productId) {
    logger.info("Received request to retrieve product with ID: {}", productId);

    Inventory product = inventoryService.getProductById(productId);

    if (product == null) {
      logger.warn("Product with ID {} not found.", productId);
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Retrieved product with ID: {}", productId);
      return ResponseEntity.ok(product);
    }
  }

  /**
   * Update product details based on ID.
   */
  @PutMapping(value = "/updateproduct/{productId}")
  public ResponseEntity<Inventory> updateProduct(@PathVariable String productId,
      @RequestBody InventoryInbound i) {
    logger.info("Received request to update product with ID: {}", productId);

    Inventory updatedProduct = inventoryService.updateProduct(productId, i);

    if (updatedProduct == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updatedProduct);
    }
  }

  /**
   * Delete product details based on ID.
   */
  @DeleteMapping(value = "/deleteproduct/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
    logger.info("Received request to delete product with ID: {}", productId);

    boolean deletionResult = inventoryService.deleteProduct(productId);

    if (deletionResult) {
      logger.info("Deleted product with ID: {}", productId);
      return ResponseEntity.ok("Product deleted successfully.");
    } else {
      logger.warn("Product with ID {} not found for deletion.", productId);
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Update quantity of product id.
   */
  @PutMapping(value = "/updatequantity/{productId}")
  public ResponseEntity<Inventory> updateProductQuantity(@PathVariable String productId,
      @RequestParam("quantity") Integer newQuantity) {
    logger.info("Received request to update quantity of product with ID: {}", productId);

    Inventory updatedProduct = inventoryService.updateProductQuantity(productId, newQuantity);

    if (updatedProduct == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updatedProduct);
    }
  }
}
