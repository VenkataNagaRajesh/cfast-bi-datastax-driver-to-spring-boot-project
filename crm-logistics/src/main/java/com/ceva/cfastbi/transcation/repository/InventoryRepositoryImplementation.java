package com.ceva.cfastbi.transcation.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ceva.cfastbi.transcation.datastax.Connection;

import com.ceva.cfastbi.transcation.udt.dto.Inventory;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;

@Repository
public class InventoryRepositoryImplementation implements InventoryRepository {
  private static final Logger logger =
      LoggerFactory.getLogger(InventoryRepositoryImplementation.class);
  @Autowired
  Connection connection;

  @Override
  public Inventory saveProduct(Inventory inv) {
    logger.info("Saving product with ID: {}", inv.getProductId());
    saveInventory().save(inv);
    logger.info("Saved product with ID: {}", inv.getProductId());
    return inv;
  }

  private Mapper<Inventory> saveInventory() {
    return connection.getManager().mapper(Inventory.class);
  }

  @Override
  public List<Inventory> getAllProducts() {
    logger.info("Retrieving all products from the database using DataStax methods.");

    ResultSet resultSet = connection.getSession().execute("SELECT * FROM inventory");
    List<Inventory> products = new ArrayList<>();

    for (Row row : resultSet) {
      Inventory inventory = new Inventory();
      inventory.setProductId(row.getString("product_id"));
      inventory.setName(row.getString("product_name"));
      inventory.setDescription(row.getString("description"));
      inventory.setPrice(row.getDouble("price"));
      inventory.setQuantity(row.getInt("quantity"));
      inventory.setCategory(row.getString("category"));
      products.add(inventory);
    }

    return products;
  }

  @Override
  public Inventory getProductById(String productId) {
    logger.info("Retrieving product with ID: {}", productId);
    ResultSet resultSet = connection.getSession()
        .execute("SELECT * FROM inventory WHERE product_id = ?", productId);

    Row row = resultSet.one();

    if (row == null) {
      logger.info("Product with ID {} not found.", productId);
      return null;
    }

    Inventory product = new Inventory();
    product.setProductId(row.getString("product_id"));
    product.setName(row.getString("product_name"));
    product.setDescription(row.getString("description"));
    product.setPrice(row.getDouble("price"));
    product.setQuantity(row.getInt("quantity"));
    product.setCategory(row.getString("category"));
    logger.info("Retrieved product with ID: {}", productId);
    return product;
  }

  @Override
  public Inventory updateProduct(Inventory inv) {
    logger.info("Updating product with ID: {}", inv.getProductId());
    saveInventory().save(inv);
    logger.info("Updated product with ID: {}", inv.getProductId());
    return inv;
  }

  @Override
  public void deleteProduct(Inventory inv) {
    logger.info("Deleting product with ID: {}", inv.getProductId());
    Mapper<Inventory> inventoryMapper = connection.getManager().mapper(Inventory.class);
    inventoryMapper.delete(inv);
    logger.info("Deleted product with ID: {}", inv.getProductId());

  }

}

