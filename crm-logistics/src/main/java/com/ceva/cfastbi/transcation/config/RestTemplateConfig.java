package com.ceva.cfastbi.transcation.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
  /**
   * restTemplate.
   * 
   * @param restTemplateBuilder RestTemplateBuilder
   * @return
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

    return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(180))
        .setReadTimeout(Duration.ofSeconds(180)).build();
  }

}
