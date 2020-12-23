package com.github.seguri.spring_oauth2.rs.config;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

  @Value("${publicKey}")
  private String publicKey;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.oauth2ResourceServer(c -> c.jwt(j -> j.decoder(jwtDecoder())));
    http.authorizeRequests().anyRequest().authenticated();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      var key = Base64.getDecoder().decode(publicKey);
      var x509 = new X509EncodedKeySpec(key);
      var rsaKey = (RSAPublicKey) keyFactory.generatePublic(x509);
      return NimbusJwtDecoder.withPublicKey(rsaKey).build();
    } catch (Exception e) {
      throw new RuntimeException("Wrong public key");
    }
  }
}
