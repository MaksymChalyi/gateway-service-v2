package com.maksimkaxxl.gatewayservicev2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class WebConfig {

  @Value("${frontend-uri}")
  private String frontEndUri;

  @Bean
  public WebSessionManager webSessionManager() {
    // Emulate SessionCreationPolicy.STATELESS
    return exchange -> Mono.empty();
  }

  @Bean
  public CorsWebFilter corsWebFilter() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(List.of(frontEndUri));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of("Location"));

    // Add a custom CORS configuration for the OAuth2 redirect endpoint
    configuration.addAllowedOriginPattern("https://accounts.google.com");
    configuration.addAllowedHeader("Authorization");
    configuration.addAllowedMethod("GET");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return new CorsWebFilter(source);
  }

}
