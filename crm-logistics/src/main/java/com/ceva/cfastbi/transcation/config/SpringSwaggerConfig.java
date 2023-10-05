package com.ceva.cfastbi.transcation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringSwaggerConfig {

  /**
   * brokerageRestApi brokerageRestApi.
   * 
   * @return
   */
  @Bean
  public OpenAPI brokerageRestApi() {
    return new OpenAPI().components(new Components())
        .info(new Info().title("CRM logistics API").description("Sample API for CRM logistics")
            .version("1.0.0-SNAPSHOT")
            .contact(new Contact().name("CRM Team").email("DG_GL-FM-CFAST-BI@cevalogistics.com")));
  }
}
