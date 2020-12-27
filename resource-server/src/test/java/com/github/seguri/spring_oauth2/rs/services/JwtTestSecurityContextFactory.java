package com.github.seguri.spring_oauth2.rs.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class JwtTestSecurityContextFactory implements WithSecurityContextFactory<WithJwtTestUser> {

  private WithJwtTestUser testUser;

  @Override
  public SecurityContext createSecurityContext(WithJwtTestUser testUser) {
    this.testUser = testUser;
    var context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(jwtAuthentication());
    return context;
  }

  private Authentication jwtAuthentication() {
    var authorities =
        Arrays.stream(testUser.authorities())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    var authentication = new JwtAuthenticationToken(jwt(), authorities);
    authentication.setAuthenticated(true);
    return authentication;
  }

  private Jwt jwt() {
    var issuedAt = Instant.now();
    var expiresAt = issuedAt.plusSeconds(3600);
    Map<String, Object> headers = Map.of("user_name", testUser.username());
    Map<String, Object> claims = Map.of("user_name", testUser.username());
    return new Jwt("token", issuedAt, expiresAt, headers, claims);
  }
}
