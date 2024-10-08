package com.douglasporto.ShopSnap.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.douglasporto.ShopSnap.domain.PagamentoComBoleto;
import com.douglasporto.ShopSnap.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PagamentoComCartao.class);
        objectMapper.registerSubtypes(PagamentoComBoleto.class);
        super.configure(objectMapper);
      };
    };
    return builder;
  }
}
