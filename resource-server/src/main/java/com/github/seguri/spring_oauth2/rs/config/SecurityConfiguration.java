package com.github.seguri.spring_oauth2.rs.config;

import static org.springframework.http.HttpMethod.DELETE;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${publicKey}")
  private String publicKey;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // Setup how Jwt is decoded (public key) and how authorities/scopes inside the Jwt are managed
    http.oauth2ResourceServer(
        c ->
            c.jwt(
                j -> {
                  j.decoder(jwtDecoder());
                  j.jwtAuthenticationConverter(jwtAuthenticationConverter());
                }));

    http.csrf().ignoringAntMatchers("/h2-console/**");
    http.headers().frameOptions().sameOrigin();

    // DELETE operations on profile and metrics can only be performed by administrators
    http.authorizeRequests()
        .mvcMatchers("/h2-console/**")
        .permitAll()
        .mvcMatchers(DELETE, "/profile/**", "/metric/**")
        .hasAuthority("write")
        .anyRequest()
        .authenticated();
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

  /**
   * By default, the JwtConverter that comes with Spring Security takes scopes from the JWT and uses
   * them as authorities. For this reason, if you have authorities=["read"] and scopes=["write"],
   * your request will be considered as "write". This converter forces the JWT to use the
   * authorities array.
   */
  private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

    converter.setJwtGrantedAuthoritiesConverter(
        j -> {
          var authorities = (List<String>) j.getClaims().get("authorities");
          return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        });

    return converter;
  }
}
