package com.github.seguri.spring_oauth2.gateway;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

  @Value("${publicKey}")
  private String publicKey;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

    return http.authorizeExchange()
        .anyExchange()
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt(c -> c.publicKey(publicKey()))
        .and()
        .build();
  }

  private RSAPublicKey publicKey() {
    try {
      var keyFactory = KeyFactory.getInstance("RSA");
      var key = Base64.getDecoder().decode(publicKey);
      var x509 = new X509EncodedKeySpec(key);
      return (RSAPublicKey) keyFactory.generatePublic(x509);
    } catch (Exception e) {
      throw new RuntimeException("Wrong public key");
    }
  }
}
