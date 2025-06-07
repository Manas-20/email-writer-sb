package com.email.writer.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsconfig {

       @Bean
       public WebMvcConfigurer corsConfigurer() {
              return new WebMvcConfigurer() {
                     @Override
                     public void addCorsMappings(CorsRegistry registry) {
                            registry.addMapping("/**") // Apply to all endpoints
                                    .allowedOrigins("https://email-writer-sb-production-a803.up.railway.app") // Your frontend origin
                                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                    .allowedHeaders("*")
                                    .allowCredentials(false) // Set to true only if using cookies/auth
                                    .maxAge(3600); // Cache preflight response for 1 hour
                     }
              };
       }
}
