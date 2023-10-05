package com.ceva.cfastbi.transcation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import com.ceva.cfastbi.transcation.datastax.Connection;


/**
 * Main Class.
 * 
 * @author Rajasekar.T
 *
 */
@SpringBootApplication(scanBasePackages = {"com.ceva", "com.ceva.cjf.rest"})
@EnableAutoConfiguration(
    exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CfastBiTransactionTemplate {

  private static final Logger LOGGER = LoggerFactory.getLogger(CfastBiTransactionTemplate.class);

  /**
   * Main Class.
   * 
   * @author Rajasekar .T
   */

  public static void main(String[] args) {
    LOGGER.info("Starting Spring Boot Application");
    SpringApplication.run(CfastBiTransactionTemplate.class, args);
  }

  @Bean
  public Connection getConnection() {
    return new Connection();
  }
}

