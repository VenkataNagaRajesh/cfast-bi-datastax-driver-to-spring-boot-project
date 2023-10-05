package com.ceva.cfastbi.transcation.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class EhcacheConfig {

  /**
  * KeyGenerator used to generate events.   
  * @author Rajasekar .T 
  * 
  */

  @Bean
  public KeyGenerator multiplyKeyGenerator() {
    return (Object target, Method method, Object... params) -> method.getName() + "_" + Arrays.toString(params);
  }

}
