package com.email.writer.app;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

       @Bean
       public WebClient webClient() {
              HttpClient httpClient = HttpClient.create()
                      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                      .responseTimeout(Duration.ofSeconds(15))
                      .doOnConnected(conn -> conn
                              .addHandlerLast(new ReadTimeoutHandler(15, TimeUnit.SECONDS)));

              // Increase memory buffer for large responses
              ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                      .codecs(configurer -> configurer
                              .defaultCodecs()
                              .maxInMemorySize(16 * 1024 * 1024)) // 16MB buffer
                      .build();

              return WebClient.builder()
                      .clientConnector(new ReactorClientHttpConnector(httpClient))
                      .exchangeStrategies(exchangeStrategies)
                      .filter(logRequest())
                      .filter(logResponse())
                      .build();
       }

       // Log request details
       private ExchangeFilterFunction logRequest() {
              return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                     System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
                     return Mono.just(clientRequest);
              });
       }

       // Log response details
       private ExchangeFilterFunction logResponse() {
              return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                     System.out.println("Response status: " + clientResponse.statusCode());
                     return Mono.just(clientResponse);
              });
       }
}