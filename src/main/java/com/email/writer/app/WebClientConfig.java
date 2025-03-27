package com.email.writer.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
       @Bean
       public WebClient webClient(WebClient.Builder builder) {
              return builder.build();
       }

       @Bean
       public WebClient.Builder webClientBuilder() {
              return WebClient.builder();
       }

       @Bean
       public ObjectMapper objectMapper() {
              return new ObjectMapper();
       }
}